import javafx.scene.control.Alert;

import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    private int port = 6666;
    private Thread run, send, receive, manage;
    //public long serverStartTime;
    private InetAddress ip;

    private Boolean running = false, raw = false, exits = false;
    private DatagramSocket socket;

    private List<Gamer> clients = new ArrayList<>();
    Connection con;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public static void main(String[] args) throws IOException {


    public Server(int port){
            this.port = port;
            try {
                socket = new DatagramSocket(port);
            } catch (SocketException ex) {
                ex.printStackTrace();
            }

            try {
                ip = InetAddress.getLocalHost();
                System.out.println("Ip of server : " + ip.getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

            run = new Thread(this, "Run Thread");
            run.start();
        }
    }
        if (clients[0].)

        String query = "INSERT INTO wyniki (name, won, lost) VALUES(?,?,?);";
        pst = con.prepareStatement(query);
        pst.setString(1, gracz.getName().getText());
        pst.setString(2, gracz.won().getText());
        pst.setString(3, gracz.lost().getText());


        pst.execute();

        pst.close();
        clearFields();
    } catch(Exception e1){
        //label.setText("SQL Error");
        System.err.println(e1);
    }
}