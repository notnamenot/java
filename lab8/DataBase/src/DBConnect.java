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
    /*public DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//!!!! ssh javatutorial20 sql
            con = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl:3306/ipachel","ipachel","vobkRh7ahTwGpMkR");
            st = con.createStatement();
        }
        catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println("VendorError: "+ex.getErrorCode());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/


    /*public void connect (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//!!!! ssh javatutorial20 sql
            con = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl:3306/","ipachel","vobkRh7ahTwGpMkR");
            st=con.createStatement();
        }
        catch(SQLException ex) {
            // handle any errors
            System.out.println("SQLException: "+ex.getMessage());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println("VendorError: "+ex.getErrorCode());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/
//https://stackoverflow.com/questions/25546417/where-can-i-download-mysql-jdbc-jar-from

    /*public void listNames(){
        try{
            connect();
            st=con.createStatement();
            // Wyciagamy wszystkie pola z kolumny nameznajdujące się w tabeli users
            rs=st.executeQuery("SELECT name FROM users");

            while(rs.next()){
                String name=rs.getString(1);
                System.out.println("Uzytkownik: "+name);
            }

        }
        catch(SQLException ex){
            // handle any errors
        }
        finally {// zwalniamy zasoby, które nie będą potrzebne
            if (rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException sqlEx){
                }
// ignore
                rs=null;
            }
            if(st!=null) {
                try{
                    st.close();
                }
                catch(SQLException sqlEx ){
                }
// ignore
                st=null;
            }
        }
    }*/

   /* public void getData() {
        try {
            String query = "select * from books";
            rs = st.executeQuery(query);
            System.out.println("Records from Database");
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println("Tytuł: " + title + "\tAuthor: " + author);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createTable() throws SQLException {
        st = con.createStatement();
        st.executeUpdate( "CREATE TABLE tabela1 ("+ "priKey INT NOT NULL AUTO_INCREMENT, "+ "nazwisko VARCHAR(64), PRIMARY KEY (priKey))");
    }

    public void addUser() throws SQLException {
        st = con.createStatement();
        st.executeUpdate("INSERT INTO tabela1 (nazwisko) "+ "values ('Bobek')");
    }*/
}
