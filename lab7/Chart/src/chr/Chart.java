package chr;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Chart extends Application {

    ArrayList<Float> coefs = new ArrayList<>();

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

    float getOd(){
        return Float.valueOf(tfield_od.getText());
    }

    float getDo(){
        return Float.valueOf(tfield_do.getText());
    }

    float getProb(){
        return Float.valueOf(tfield_prob.getText());
    }

    private boolean button_draw_check(Button coefs, Button zakres, Button prob) {
        if (!coefs.isDisable() && !zakres.isDisable() && !prob.isDisable())
            return false;
        else
            return true;
    }

    public static String superscript(String str) {
        //str = str.replaceAll("0", "⁰");

        str = str.replaceAll("(\\^1)", "");
        str = str.replaceAll("(\\^2)", "²");
        str = str.replaceAll("(\\^3)", "³");
        str = str.replaceAll("(\\^4)", "⁴");
        str = str.replaceAll("(\\^5)", "⁵");
        str = str.replaceAll("(\\^6)", "⁶");
        str = str.replaceAll("(\\^7)", "⁷");
        str = str.replaceAll("(\\^8)", "⁸");
        str = str.replaceAll("(\\^9)", "⁹");

        str = str.replaceAll("\\.0","");
        str = str.replaceAll("(1?x)","x");
        return str;
    }

    String polynomial(ArrayList<Float> coefs) {
        String title_poly = "";
        float co;
        for (int i = 0; i < coefs.size(); ++i ) {
            co = coefs.get(i);

            if (co == 0)
                continue;
            if (co > 0 && i!=0)
                title_poly += "+";

            title_poly += co;

            if (i+1 == coefs.size())   //jeśli wyraz wolny
                break;

            title_poly += "x^";
            title_poly += coefs.size()-1-i;
        }

        title_poly = superscript(title_poly);
        System.out.println(title_poly);

        return title_poly;
    }

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

     LineChart poly_draw(float od, float do_, float prob) {

         NumberAxis xAxis = new NumberAxis(od-(do_-od)/10, do_+(do_-od)/10, (do_-od)/10);
         xAxis.setLabel("x");

         float min = poly_min_max(od,do_,prob).get(0);
         float max = poly_min_max(od,do_,prob).get(1);
         //Defining the y axis

         //DecimalFormat df = new DecimalFormat("#.##");
         NumberAxis yAxis = new NumberAxis(min -(max-min)/10, max+(max-min)/10, (max-min)/10);
         yAxis.setLabel("y");

         //Creating the line chart
         LineChart linechart = new LineChart(xAxis, yAxis);
         //linechart.getData().clear();

         linechart.setTitle("Wykres wielominu " + polynomial(coefs));

         //Prepare XYChart.Series objects by setting data
         XYChart.Series series = new XYChart.Series();

         //series.setName("Twoja funkcja: " + polynomial(coefs));

         float interval = (do_ - od) / prob;
         for(int i = 0; i <= prob; ++i) {
             //System.out.println(od + " " + poly(od));
             series.getData().add(new XYChart.Data(od, poly(od)));
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
            tfield_coef.setText("Podawaj współczynniki po jednym");
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


        //So you have only one Scene per Stage but possibly several Panes (a Pane is-a Node).

        FlowPane pane_text = new FlowPane();
        pane_text.setHgap(10);
        pane_text.setVgap(10);
        pane_text.setPadding(new Insets(10, 10, 10, 10));
        //.setBackground();
        //pane_text.setMinSize(600, 50);
        //myPane.setBottom(label_wsp);
        //myPane.setBottom(textfield_coef);
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
            tfield_coef.setPromptText("Kolejny współczynnik");
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
            linechart = poly_draw(od, do_, prob);
            myPane.setBottom(linechart);
            //button_draw.setVisible(false);
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


        //myPane.setTop(poly_draw(od,  do_,  prob));
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
        Scene scene = new Scene(root, 800, 600);

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
