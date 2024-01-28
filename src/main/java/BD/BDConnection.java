package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDConnection {
    private static Connection con = null;

    static
    {
        String user = "postgres";
        String pass = "5EqvIKJQL0D7T8xT";
        String url = "jdbc:postgresql://db.wthwdlfeyraxfycluznd.supabase.co:5432/postgres?user=postgres&password="+pass;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
