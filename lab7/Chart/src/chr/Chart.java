package chr;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

import mylib.MyLib;

public class Chart extends Application {

    private int WIDTH = 700;
    private int HEIGHT = 500;

    ArrayList<Float> coefs;

    Text label_wsp ;
    Text label_od;
    Text label_do ;
    Text label_prob;

    //Creating buttons
    Button button_add_wsp;
    Button button_add_zakres;
    Button button_add_prob;
    Button button_draw;
    Button button_new_chart;
    Button button_redraw;

    TextField tfield_coef;
    TextField tfield_od;
    TextField tfield_do;
    TextField tfield_prob ;

    float od, do_, prob;

    LineChart linechart;


    private boolean button_draw_check(Button coefs, Button zakres, Button prob) {
        if (!coefs.isDisable() && !zakres.isDisable() && !prob.isDisable())
            return false;
        else
            return true;
    }

    private boolean check_zakres(float od, float do_) {
        return do_ < od;
    }


    private LineChart poly_draw(float od, float do_, float prob) {
        MyLib myLib = new MyLib();
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");

        //float min = myLib.poly_min_max(od,do_,prob,coefs).get(0);
        //float max = myLib.poly_min_max(od,do_,prob,coefs).get(1);

        //Defining the y axis
        //DecimalFormat df = new DecimalFormat("#.##");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("w(x)");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        linechart.setTitle("Wykres wielomianu W(x) = " + myLib.polynomial(coefs));

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        linechart.setLegendVisible(false);

        float interval = (do_ - od) / prob;
        for(int i = 0; i <= prob; ++i) {
            series.getData().add(new XYChart.Data(od, myLib.poly(od,coefs)));
            od += interval;
        }

        //Setting the data to Line chart
        linechart.getData().add(series);

        return linechart;

    }

    @Override
    public void start(Stage stage) {

        coefs = new ArrayList();

        label_wsp = new Text("Współczynniki:");
        label_od = new Text("Zakres od:");
        label_do = new Text("do");
        label_prob = new Text("Próbkowanie:");

        //Creating buttons
        button_add_wsp = new Button("Add");
        button_add_zakres = new Button("Add");
        button_add_prob = new Button("Add");
        button_draw = new Button("Draw");
        button_new_chart = new Button("Draw New Chart");
        button_redraw = new Button("Redraw");


        button_add_wsp.setDisable(true);
        button_add_zakres.setDisable(true);  // Initally text box was empty so button was disable
        button_add_prob.setDisable(true);
        button_draw.setDisable(true);

        button_redraw.setVisible(false);
        button_new_chart.setVisible(false);

        tfield_coef = new TextField();
            tfield_coef.setText("First");
        tfield_od = new TextField("");
        tfield_do = new TextField("");
        tfield_prob = new TextField("");

        tfield_coef.setPrefWidth(50);
        tfield_od.setPrefWidth(50);
        tfield_do.setPrefWidth(50);
        tfield_prob.setPrefWidth(50);

        // force the field to be numeric only
        tfield_coef.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^-?\\d*\\,?\\d*")) {
                    tfield_coef.setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                    button_add_wsp.setDisable(false);

            }
        });

        // force the field to be numeric only
        tfield_od.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^-?\\d*\\,?\\d*")) {
                    tfield_od.setText(newValue.replaceAll("[^\\d]", ""));
                }

            }
        });

        tfield_do.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^-?\\d*\\,?\\d*")) {
                    tfield_do.setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                    button_add_zakres.setDisable(false);

            }
        });

        // force the field to be numeric only
        tfield_prob.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d+")) {
                    tfield_prob.setText(newValue.replaceAll("[^\\d]", ""));
                }
                else
                    button_add_prob.setDisable(false);
            }
        });

        FlowPane pane_text = new FlowPane();
        pane_text.setHgap(10);
        pane_text.setVgap(10);
        pane_text.setPadding(new Insets(10, 10, 10, 10));
        pane_text.setPrefWrapLength(WIDTH-20);
        pane_text.setStyle("-fx-background-color: DAE6F3;");

        pane_text.getChildren().add(label_wsp);
        pane_text.getChildren().add(tfield_coef);
        pane_text.getChildren().add(button_add_wsp);
        pane_text.getChildren().add(label_od);
        pane_text.getChildren().add(tfield_od);
        pane_text.getChildren().add(label_do);
        pane_text.getChildren().add(tfield_do);
        pane_text.getChildren().add(button_add_zakres);
        pane_text.getChildren().add(label_prob);
        pane_text.getChildren().add(tfield_prob);
        pane_text.getChildren().add(button_add_prob);
        pane_text.getChildren().add(button_draw);
        pane_text.getChildren().add(button_redraw);
        pane_text.getChildren().add(button_new_chart);

        BorderPane myPane = new BorderPane();
        myPane.setTop(pane_text);


        button_add_wsp.setOnAction(actionEvent -> {
            coefs.add(Float.valueOf(tfield_coef.getText()));
            tfield_coef.setText("");
            tfield_coef.setPromptText("Next");
            button_draw.setDisable(button_draw_check(button_add_wsp,button_add_zakres,button_add_prob));
        } );

        button_add_zakres.setOnAction(actionEvent -> {

            od = Float.valueOf(tfield_od.getText());
            do_ = Float.valueOf(tfield_do.getText());
            tfield_od.setText("");
            tfield_do.setText("");
            button_draw.setDisable(button_draw_check(button_add_wsp,button_add_zakres,button_add_prob));
            System.out.println("button_add_zakres pressed od= " +od+" do= "+do_ );
        } );


        button_add_prob.setOnAction(actionEvent -> {
            prob = Float.valueOf(tfield_prob.getText());
            tfield_prob.setText("");
            button_draw.setDisable(button_draw_check(button_add_wsp,button_add_zakres,button_add_prob));
        } );


        button_draw.setOnAction(actionEvent -> {
            if (!check_zakres(od, do_) == true) {
                float tmp = od;
                od = do_;
                do_ = tmp;
            }

            linechart = poly_draw(od, do_, prob);
            myPane.setBottom(linechart);

            button_draw.setDisable(true);
            button_redraw.setVisible(true);
            button_new_chart.setVisible(true);
            button_add_wsp.setDisable(true);
        });

        button_redraw.setOnAction(actionEvent -> {
            myPane.getChildren().remove(linechart);
            linechart = poly_draw(od, do_, prob);
            myPane.setBottom(linechart);
        });

        button_new_chart.setOnAction(actionEvent -> {
            coefs.clear();
            myPane.getChildren().remove(linechart);
            button_draw.setDisable(true);
            button_new_chart.setVisible(false);
            button_draw.setVisible(true);
            button_redraw.setDisable(true);
        } );


        //Creating a Group object
        //Group root = new Group(linechart,pane_text);
        Group root = new Group();
        //Retrieving the observable list object
        ObservableList list = root.getChildren();//The getChildren() method of the Group class gives you an object of the ObservableList class which holds the nodes. We can retrieve this object and add nodes to it
        //Setting the text object as a node
        list.add(myPane);

        //Creating a scene object
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setResizable(false);
        //Setting title to the Stage
        stage.setTitle("Line Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}