package ktr;



public class MainServer {
    static int port;
    Server server;
    public MainServer(int port){
        this.port = port;
        this.server = new Server(port);
        System.out.println("Server Started ");

    }
    public static void main(String[] args){

        int port = 6666;
        new MainServer(port);
    }


}
