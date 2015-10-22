package ConnexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaConnexion {

    private Connection cnt;


    public MaConnexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String user = "sa";
        String mdp = "sa";
        String url = "jdbc:sqlserver://localhost:1433; "
                + "databaseName=Librairie";
        cnt = DriverManager.getConnection(url, user, mdp);
    }

    public PreparedStatement createStatement(String req) throws SQLException {
        return cnt.prepareStatement(req);
    }

    public void close() throws SQLException
    {
        
        if (cnt!=null) {
            cnt.close();
            
        }
    }
  
}
