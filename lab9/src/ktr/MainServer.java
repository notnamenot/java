package ktr;

//import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class MainServer {
    static int port;
    Server server;
    public MainServer(int port){
        this.port = port;
        this.server = new Server(port);
        System.out.println("Server Started ");

    }
    public static void main(String[] args){
        /*int port;
        if(args.length!=1){
            System.out.println("Please Enter port number For Server ");
            return;
        }

        port=Integer.parseInt(args[0]);*/
        int port = 6666;
        new MainServer(port);
    }


}