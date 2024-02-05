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
        String pwd = "...";
        String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.wthwdlfeyraxfycluznd&password="+pwd;
        String usr = "postgres";
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
