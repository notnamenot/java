package db;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DB extends Application {
    private Connection con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private Button but_load;
    private Label label_add_book;
    private TextField tfield_isbn, tfield_title, tfield_author, tfield_year, tfield_search;

    private TableView<Book> table;
    private final ObservableList<Book> data = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) {
        CheckConnection();

        primaryStage.setTitle("Baza danych");

        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout,840,500,Color.rgb(0,0,0,0.4));


        VBox fields = new VBox(5);

        but_load = new Button("Show Table");
        but_load.setMaxHeight(300);
        but_load.setFont(Font.font("SanSerif",20));
        but_load.setOnAction(e -> refreshTable());

        tfield_search = new TextField();
        tfield_search.setFont(Font.font("SanSerif",15));
        tfield_search.setPromptText("Search ISBN or Author (surname)");
        tfield_search.setMinHeight(50);
        tfield_search.setMaxWidth(300);

        label_add_book = new Label("Add db.Book");
        label_add_book.setFont(new Font("SanSerif",20));

        tfield_isbn = new TextField();
        tfield_isbn.setFont(Font.font("SanSerif",20));
        tfield_isbn.setPromptText("isbn");
        tfield_isbn.setMaxWidth(300);

        tfield_title = new TextField();
        tfield_title.setFont(Font.font("SanSerif",20));
        tfield_title.setPromptText("title");
        tfield_title.setMaxWidth(300);

        tfield_author = new TextField();
        tfield_author.setFont(Font.font("SanSerif",20));
        tfield_author.setPromptText("author");
        tfield_author.setMaxWidth(300);

        tfield_year = new TextField();
        tfield_year.setFont(Font.font("SanSerif",20));
        tfield_year.setPromptText("year");
        tfield_year.setMaxWidth(300);

        Button but_save = new Button("save");
        but_save.setFont(Font.font("SanSerif",15));
        but_save.setOnAction(e -> {
            if (validateIsbn() && validateTitle() && validateAuthor() && validateYear() && validateFields()) {
                try {

                    String query = "INSERT INTO books (isbn,title,author,year) VALUES(?,?,?,?);";
                    pst = con.prepareStatement(query);
                    pst.setString(1, tfield_isbn.getText());
                    pst.setString(2, tfield_title.getText());
                    pst.setString(3, tfield_author.getText());
                    pst.setString(4, tfield_year.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("db.Book added");
                    alert.showAndWait();

                    pst.execute();

                    pst.close();
                    clearFields();
                } catch(Exception e1){
                    System.err.println(e1);
                }
                refreshTable();
            }
        });


        fields.getChildren().addAll(but_load,tfield_search, label_add_book, tfield_isbn, tfield_title, tfield_author, tfield_year,but_save);
        layout.setCenter(fields);
        BorderPane.setMargin(fields, new Insets(10,0,0,10)); //top,right,bottom,left



        table = new TableView<>();

        TableColumn col1 = new TableColumn("ISBN");
        col1.setMinWidth(120);
        col1.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn col2 = new TableColumn("TITLE");
        col2.setMinWidth(200);
        col2.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn col3 = new TableColumn("Author");
        col3.setMinWidth(150);
        col3.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn col4 = new TableColumn("Year");
        col4.setMinWidth(40);
        //col4.setMaxWidth(50);
        col4.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.getColumns().addAll(col1,col2,col3,col4);

        //table.setTableMenuButtonVisible(true);

        layout.setRight(table);
        BorderPane.setMargin(table, new Insets(10,10,10,10));



        FilteredList<Book> filtered_isbn = new FilteredList<>(data, e -> true);
        tfield_search.setOnKeyReleased(e -> {
            tfield_search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtered_isbn.setPredicate((Predicate<? super Book>) book -> {
                    if(newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (book.getIsbn().contains(newValue)) {
                        return true;
                    }
                    else if (book.getAuthor().toLowerCase().substring(book.getAuthor().toLowerCase().indexOf(" ")+1).contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Book> sortedData = new SortedList<>(filtered_isbn);
            sortedData.comparatorProperty().bind(table.comparatorProperty());//binding list to table
            table.setItems(sortedData);
        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }




    private boolean validateIsbn() {
        Pattern pattern = Pattern.compile("[0-9]{13}");
        Matcher matcher = pattern.matcher(tfield_isbn.getText());
        if (matcher.find() && matcher.group().equals(tfield_isbn.getText())) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate ISBN");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid ISBN!");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validateTitle() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]+");
        Matcher matcher = pattern.matcher(tfield_title.getText());
        if (matcher.find() && matcher.group().equals(tfield_title.getText())) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate ISBN");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid title!");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validateAuthor() {
        Pattern pattern = Pattern.compile("[a-zA-Z]+ [a-zA-Z]+");
        Matcher matcher = pattern.matcher(tfield_author.getText());
        if (matcher.find() && matcher.group().equals(tfield_author.getText())) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Author!");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid author!");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validateYear() {
        //if (tfield_year.getText().isEmpty()){
        //    return true;}
        Pattern pattern = Pattern.compile("[12][0-9]{3}");
        Matcher matcher = pattern.matcher(tfield_year.getText());
        if (matcher.find() && matcher.group().equals(tfield_year.getText())) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Year");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid year!");
            alert.showAndWait();

            return false;
        }
    }

    private boolean validateFields() {
        if (tfield_isbn.getText().isEmpty() ||
                tfield_title.getText().isEmpty() ||
                tfield_author.getText().isEmpty() ||
                tfield_year.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Nie podano wszystkich danych");
            alert.showAndWait();

            return false;
        }
        return true;
    }


    private void refreshTable() {
        data.clear();
        try {
            String query = "SELECT * FROM books;";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while(rs.next()) {
                data.add(new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("year")
                ));
            }
            table.setItems(data);

            pst.close();
            rs.close();

        } catch(Exception e2) {
            System.out.println(e2);
        }
    }



    private void clearFields() {
        tfield_isbn.clear();
        tfield_title.clear();
        tfield_author.clear();
        tfield_year.clear();
    }

    private void CheckConnection(){

        for (int i=0; i< 3; ++i) {
            con = DBConnect.DBConnector();
            if (con == null){
                System.out.println("nope");
                continue;
            }
            else
                return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Check Connection");
        alert.setHeaderText(null);
        alert.setContentText("Połączenie z bazą nie powiodło się trzykrotnie!");
        alert.showAndWait();

        System.exit(1);

    }

    public static void main(String[] args){
        launch(args);
    }

}