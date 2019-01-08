import java.net.InetAddress;

public class Gamer {
    String name;
    int won;
    int lost;

    public Gamer(String name, int won, int lost){
        this.name=name;
        this.won=won;
        this.lost=lost;
    }

    String getName() { return name; }
    int getWon() { return won; }
    int getLost() { return lost; }

    void won() { won++; }
    void lost() { lost++; }
}
