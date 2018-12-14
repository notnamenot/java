import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multiThreadServer extends Thread{
    static int nrOfThreads = 1;

    public static void main(String[] args)  throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
            ExecutorService es = Executors.newFixedThreadPool(nurOfThreads);
            while(true){
                ThreadClient client = new ThreadClient(serverSocket.accept()) ;
                es.submit(client);
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1); //error
        }
    }
}



