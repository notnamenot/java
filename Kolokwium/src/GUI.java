import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends Application {

    Connection con;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9, bsend;
    GridPane grid;



    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Kółko i krzyżyk");

        CheckConnection();

        b1 = new Button("");
        b2 = new Button("");
        b3 = new Button("");
        b4 = new Button("");
        b5 = new Button("");
        b6 = new Button("");
        b7 = new Button("");
        b8 = new Button("");
        b9 = new Button("");
        bsend = new Button("Graj!");


        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.add(b1,0,0);
        grid.add(b2,1,0);
        grid.add(b3,2,0);
        grid.add(b4,0,1);
        grid.add(b5,1,1);
        grid.add(b6,2,1);
        grid.add(b7,0,2);
        grid.add(b8,1,2);
        grid.add(b1,2,2);
        b1.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if (e.getClickCount() == 2) {
                            b1.setText("x");
                    }
                    else if (e.getClickCount() == 2) {
                        b1.setText("o");
                    }
                    else
                        b1.setText("");
                }
        });
        b2.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b2.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b2.setText("o");
                }
                else
                    b2.setText("");
            }
        });
        b3.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b3.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b3.setText("o");
                }
                else
                    b3.setText("");
            }
        });
        b4.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b4.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b4.setText("o");
                }
                else
                    b4.setText("");
            }
        });
        b5.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b5.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b5.setText("o");
                }
                else
                    b5.setText("");
            }
            //}
        });
        b6.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b6.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b6.setText("o");
                }
                else
                    b6.setText("");
            }
            //}
        });
        b7.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b7.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b7.setText("o");
                }
                else
                    b7.setText("");
            }
            //}
        });
        b8.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b8.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b8.setText("o");
                }
                else
                    b8.setText("");
            }
            //}
        });
        b9.setOnMouseClicked(e ->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (e.getClickCount() == 2) {
                    b9.setText("x");
                }
                else if (e.getClickCount() == 2) {
                    b9.setText("o");
                }
                else
                    b9.setText("");
            }
            //}
        });

        Scene newScene = new Scene(grid,500,550,Color.rgb(0,0,0,0.4));
        primaryStage.setScene(newScene);
        primaryStage.show();
    }


    String hostName = "localhost";//args[0];
    int portNumber = 6666;//Integer.parseInt(args[1]);

    public static void main(String[] args){
        launch(args);
    }



    public void CheckConnection(){
        //con = DBConnect.DBConnector();
        /*if (con == null) {
            System.out.println("Connection Not Successful");

            System.exit(1);
        }
        else {
            System.out.println("Connection Successful");
        }*/
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
}