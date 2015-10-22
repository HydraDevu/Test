package beans;

import ConnexionBDD.MaConnexion;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class beanLogin implements Serializable {

    public beanLogin() {

    }

    public int check(String user, String mdp) {

        try {
            MaConnexion mc = new MaConnexion();

            String req = "Select * from Membre "
                    + "Where mailMembre = '" + user + "' "
                    + "And mdpMembre = '" + mdp + "' "
                    + "And actifMembre = 1 ";

            PreparedStatement pstm = mc.createStatement(req);
            ResultSet rs = pstm.executeQuery();
            System.out.println(req);

            if (rs.next()) {
                return rs.getInt("idMembre");
            }

            mc.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

}
