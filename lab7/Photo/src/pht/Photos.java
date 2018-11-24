package pht;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class Photos extends Application {

    Stage stage;
    static String path;

    static final String[] EXTENSIONS = new String[]{ "gif", "png", "bmp", "jpg", "jpeg" };

    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };


    @Override
    public void start(Stage primaryStage) throws Exception {
        //String path = "my_photos";
        File folder = new File(path);
        if (!folder.isDirectory()) {
            System.out.println("Błędna ścieżka do katalogu!");
            System.exit(-1);
        }

        stage = primaryStage;
        stage.setTitle("Photo Viewer");
        ScrollPane root = new ScrollPane();
        TilePane tile = new TilePane(Orientation.HORIZONTAL);
        root.setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);
        tile.setVgap(25);


        if (folder.isDirectory()) {             // make sure it's a directory
            for (final File file : folder.listFiles(IMAGE_FILTER)) {

                ImageView imageView;
                imageView = createImageView(file);
                tile.getChildren().add(imageView);
            }
        }




        File[] listOfFiles = folder.listFiles();

        /*for (final File file : listOfFiles) {

            ImageView imageView;
            imageView = createImageView(file);
            tile.getChildren().add(imageView);
        }*/


        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        root.setFitToWidth(true);
        root.setContent(tile);

        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private ImageView createImageView(final File imageFile) {

        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 250, 0, true, true); //Image(InputStream is, double requestedWidth, double requestedHeight, boolean preserveRatio, boolean smooth)
            imageView = new ImageView(image);
            //imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 2){
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                //imageView.setStyle("-fx-background-color: PINK");
                                imageView.setFitHeight(stage.getHeight() - 100);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: WHITE");
                                Stage newStage = new Stage();
                                newStage.setWidth(stage.getWidth()-200);
                                newStage.setHeight(stage.getHeight()-200);
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj pełną ścieżkę do katalogu: ");
        path = sc.next();
        launch(args);
    }

}