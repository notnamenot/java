package ktr;

import java.net.InetAddress;

public class ServerClient {
    private int port;
    private String name;
    private InetAddress address;
    private int ID;
    //public int attempt = 0;

    public ServerClient(String name,InetAddress address, int port,final int ID){
        this.port=port;
        this.name=name;
        this.address=address;
        this.ID=ID;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
