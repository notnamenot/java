package chr;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;

public class Chart extends Application {

    ArrayList<Float> coefs = new ArrayList<>();



    float poly(float x) {
        float y = 0;
        for (int i = coefs.size(), j= 0; i > 0; --i, ++j) {
            y += Math.pow(x, i-1)*coefs.get(j);
        }
        return y;
    }

    ArrayList<Float> poly_min_max(float from, float to, float fs) {
        float min = from;
        float max = from;
        float interval = (to - from) / fs;
        for (int i = 0; i < fs + 1; ++i) {                         //znajdowanie ymin i ymax
            min = poly(from + i * interval) < min ? poly(from + i * interval) : min;
            max = poly(from + i * interval) > max? poly(from + i * interval) : max;
         }
         return new ArrayList<Float>(Arrays.asList(min,max));
    }

    // void poly_draw

    @Override
    public void start(Stage stage) {

        //ArrayList coefs = new ArrayList();

        Text label_wsp = new Text("Współczynniki:");
        Text label_od = new Text("Zakres od:");
        Text label_do = new Text("do");
        Text label_prob = new Text("Próbkowanie:");

        //Creating buttons
        Button button_add = new Button("Add");
        Button button_draw = new Button("Draw");

        TextField tfield_coef = new TextField();
        tfield_coef.setPromptText("Kolejny współczynnik");
        TextField tfield_od = new TextField();
        TextField tfield_do = new TextField();
        TextField tfield_prob = new TextField();

        tfield_coef.setPrefWidth(20);
        tfield_od.setPrefWidth(20);
        tfield_do.setPrefWidth(20);
        tfield_prob.setPrefWidth(20);

        //So you have only one Scene per Stage but possibly several Panes (a Pane is-a Node).

        FlowPane pane_text = new FlowPane();
        pane_text.setHgap(10);
        pane_text.setVgap(10);
        //pane_text.setMinSize(600, 50);
        //myPane.setBottom(label_wsp);
        //myPane.setBottom(textfield_coef);
        pane_text.getChildren().add(label_wsp);
        pane_text.getChildren().add(tfield_coef);
        pane_text.getChildren().add(button_add);
        pane_text.getChildren().add(label_od);
        pane_text.getChildren().add(tfield_od);
        pane_text.getChildren().add(label_do);
        pane_text.getChildren().add(tfield_do);
        pane_text.getChildren().add(label_prob);
        pane_text.getChildren().add(tfield_prob);
        pane_text.getChildren().add(button_draw);


        button_add.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("buttonAdd pressed ");
                coefs.add(Float.valueOf(tfield_coef.getText()));
                tfield_coef.setText(null);
                tfield_coef.setPromptText("Kolejny współczynnik");
            }
        }));

        /*button_draw.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("buttonDraw pressed ");

            }
        }));*/

        BorderPane myPane = new BorderPane();
        myPane.setBottom(pane_text);
        //HBox hbox = new HBox();//The HBox l
        // ayout arranges all the nodes in our application in a single horizontal row.
        //hbox.setSpacing(10);



        //for ()


        float od = Float.valueOf(tfield_od.getText());
        float do_ = Float.valueOf(tfield_do.getText());
        float prob = Float.valueOf(tfield_prob.getText());
        //GridPane pane_chart = new GridPane();
        //Defining the x axis
        //NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
        //xAxis.setLabel("Years");
        NumberAxis xAxis = new NumberAxis(od, do_, 15);
        xAxis.setLabel("x");


        float min = poly_min_max(od,do_,prob).get(0);
        float max = poly_min_max(od,do_,prob).get(1);
        //Defining the y axis
        //NumberAxis yAxis = new NumberAxis   (0, 350, 50);
        //yAxis.setLabel("No.of schools");
        NumberAxis yAxis = new NumberAxis(min, max, 15);
        yAxis.setLabel("y");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);
       // linechart.getData().clear();
        //pane_text.setAlignment(Pos.BOTTOM_LEFT);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("No of schools in an year");

        float interval = (od - do_) / prob;
        for(int i = 0; i < prob; ++i) {
            series.getData().add(new XYChart.Data(od, poly(od)));
            od += interval;
        }

        /*series.getData().add(new XYChart.Data(1970, 15));
        series.getData().add(new XYChart.Data(1980, 30));
        series.getData().add(new XYChart.Data(1990, 60));
        series.getData().add(new XYChart.Data(2000, 120));
        series.getData().add(new XYChart.Data(2013, 240));
        series.getData().add(new XYChart.Data(2014, 300));*/

        //Setting the data to Line chart
        linechart.getData().add(series);

        myPane.setTop(linechart);
        //pane_chart.setAlignment(Pos.TOP_CENTER);
        //pane_text.add(linechart,1,1);

        //Creating a Group object
        //Group root = new Group(linechart,pane_text);
        Group root = new Group();
        //Retrieving the observable list object
        ObservableList list = root.getChildren();//The getChildren() method of the Group class gives you an object of the ObservableList class which holds the nodes. We can retrieve this object and add nodes to it

        //Setting the text object as a node
        list.add(myPane);
        //list.add(pane_chart);



        //Creating a scene object
        Scene scene = new Scene(root, 600, 600);

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