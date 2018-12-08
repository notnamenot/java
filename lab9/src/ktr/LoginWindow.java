package ktr;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import ktr.Client;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

//import static javafx.application.Application.launch;

public class LoginWindow extends Application implements Runnable{

    private int WIDTH = 400;
    private int HEIGHT = 300;

    private Stage window;
    private Scene scene, scene2;
    private Label label_welcome;
    private TextField tfield_user, tfield_message;
    private Button button_login, button_send;
    private TextArea textArea = new TextArea();
    private VBox vBox;

    //List<ServerClient> connectedClients = new ArrayList<>();

    private InetAddress ip;
    private DatagramSocket socket;
    private int port = 6666, ID = -1;
    private Thread send, receive, listen, run, updateClients;
    private boolean running = false;
    private boolean text = false, typeAttempt = true, seenAttempt = false, messageReceived = false, clientStatus;
    String disconnectedUser;


    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Login window");

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        label_welcome = new Label("Welcome");
        label_welcome.setFont(Font.font("Serif", FontWeight.BOLD, 40));

        Separator separator = new Separator();
        separator.setVisible(true);
        separator.setPadding(new Insets(10, 0, 0, 0));

        Label label_login = new Label("Enter your login");
        label_login.setFont(Font.font("Serif", FontWeight.BOLD, 20));
        tfield_user = new TextField();
        tfield_user.setMaxWidth(100);
        button_login = new Button("Login");
        button_login.setOnAction(e -> {
            window.setScene(clientWindow(  ));
        });

        vBox.getChildren().addAll(label_welcome, separator, label_login, tfield_user, button_login);

        scene = new Scene(vBox,WIDTH,HEIGHT);
        window.setScene(scene);
        window.show();
    }

    private Scene clientWindow() {
        boolean checkConnect = openConnection();

        if (checkConnect) {
            String message = "/c/" + tfield_user.getText() + "/e/";
            send(message.getBytes());
        }

        running = true;
        run = new Thread(this,"Running");
        run.start();

        window.setTitle(tfield_user.getText() + " Window");

        textArea.setEditable(false);
        textArea.setMaxWidth(500);
        textArea.setMinHeight(400);
        textArea.setPadding(new Insets(10,10,10,10));

        tfield_message = new TextField();
        tfield_message.setPadding(new Insets(10));
        tfield_message.setPromptText("Type message here");
        tfield_message.setMinWidth(500);
        tfield_message.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                send(tfield_message.getText(), 0);
                typeAttempt = true;
            }
            else {
                send(" :is Typing",1);
            }
        });

        button_send = new Button("Send");
        button_send.setOnAction(e -> {

        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(tfield_message,button_send);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(textArea,hBox);

        //BorderPane borderPane= new BorderPane();
        //borderPane.setTop(

        scene2 = new Scene(vBox,600,500);

        if (messageReceived) {
            scene2.setOnMousePressed(e -> {
                seenAttempt = true;
                send("message seen",3);
            });
        }

        return scene2;
    }

    private boolean openConnection() {
        try{
            ip = InetAddress.getLocalHost();;//.getByName("localhost");
            System.out.println(ip);
            socket = new DatagramSocket();
        } catch (UnknownHostException | SocketException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void send(String message, int text) {

        switch (text) {
            case 0:
                if (message.isEmpty()) {
                    System.out.println("Message is empty");
                    return;
                }
                /*if (message.startsWith( "/time/")) {
                    send(message.getBytes());
                } else if (message.startsWith("/ip/")) {
                    send(message.getBytes());
                } else if (message.startsWith("/number/")) {
                    send(message.getBytes());
                }*/
                message = "/m/" + tfield_user.getText() + " : " + message + "/e/";
                send(message.getBytes());
                tfield_message.clear();
                break;

            case 1:
                message = "/t/" + tfield_user.getText() + " IS TYPING ";
                send(message.getBytes());
                // messageField.clear();
                break;
            case 2://disconnected
                send(message.getBytes());
                break;
            case 3://seen
                message = "/s/" + "message seen" + "/e/";
                send(message.getBytes());
                break;
            default:
                break;
        }

    }

    private void send(final byte[] data) {
        send = new Thread("Send tread") {
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
    }



    private String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String string = new String(packet.getData());
        return string;
    }

    private void listen() { // for checking to client and server
        listen = new Thread("Listen Thread") {
            int attempt;

            @Override
            public void run() {
                while (running) {
                    String message = receive();
                    if (message.startsWith("/c/")) {
                        ID = Integer.parseInt(message.split("/c/|/e/")[1]);
                        //console("Successfully Connected to Server! Client ID : " + ID);
                    } else if (message.startsWith("/d/")) {
                        String disconnectUser = message.split("/d/|/i/")[1];
                        String disconnectID = message.split("/i/|/e/")[1];
//                        String disconnectMessage = message.split("/d/|/e/")[1];
                        // alertBox("Client Disconnected",disconnectMessage+ " left the Chat");
                        System.out.println("Disconnected User : " + disconnectUser);
                        System.out.println("Disconnected ID   : " + disconnectID);
                        disconnectedUser = disconnectUser;
                        //console(disconnectUser + " left the Chat");
                        clientStatus = true;
                        System.out.println("sent cleint status " + clientStatus);
                    } else if (message.startsWith("/m/")) {
                        String text = message.substring(3);
                        text = text.split("/e/")[0];
                        //console(text);
                        messageReceived = true;
                    } else if (message.startsWith("/time/")) {
                        String receivedTime = message.substring(6);
                        receivedTime = receivedTime.split("/e/")[0];
                        // System.out.println(receivedTime.length());
                        long serverTime = Long.parseLong(receivedTime);
                        long currentTime = System.currentTimeMillis();
                        long totalTime = currentTime - serverTime;
                        double sec = totalTime / 1000;

                       // console(sec + " sec");
                        System.out.println(sec + " seconds");
                    } else if (message.startsWith("/ip/")) {
                        String receivedIp = message.substring(4);
                        receivedIp = receivedIp.split("/e/")[0];
                        System.out.println("server ip : " + receivedIp);
                        //console("server ip : " + receivedIp);
                    } else if (message.startsWith("/number/")) {
                        String number = message.substring(8);
                        number = number.split("/e/")[0];
                        System.out.println("Number of clients located : " + number);
                    } else if (message.startsWith("/s/")) {
                        //seenAttempt=!seenAttempt;
                        if (seenAttempt) {
                            String seenStatus = message.split("/s/|/e/")[1];
                           // console(seenStatus);
                            seenAttempt = false;
                        }
                    }
                    if (message.startsWith("/t/")) {
                        if (typeAttempt) {
                            String textStatus = message.split("/t/")[1];
                            System.out.println(textStatus);
                            //console(textStatus);
                            typeAttempt = false;
                        }

                    }
                }
            }
        };
        listen.start();
    }

    @Override
    public void run() {
        listen();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

//https://github.com/ashmeet4293/Chat-Application-in-java-using-javafx/blob/master/src/LoginWindow.java