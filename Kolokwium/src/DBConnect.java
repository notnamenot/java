import java.sql.*;

public class DBConnect {
    //Connection con = null;
    //Statement st = null;
    //ResultSet rs = null;

    public static Connection DBConnector() {
        try {
            Connection con;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl:3306/ipachel","ipachel","vobkRh7ahTwGpMkR");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
