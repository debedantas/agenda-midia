package BD;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con = null;
        String url = "jdbc:postgresql://db.wthwdlfeyraxfycluznd.supabase.co:5432/postgres?user=postgres&password=5EqvIKJQL0D7T8xT";
        String usr = "postgres";
        String pwd = "5EqvIKJQL0D7T8xT";
        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(url, usr, pwd);

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from \"Users\"");

            while (rs.next()) {
                System.out.println(rs.getString("usuario"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
