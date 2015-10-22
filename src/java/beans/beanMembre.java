package beans;

import ConnexionBDD.MaConnexion;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class beanMembre implements Serializable {

    private int id;
    private int type;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String mail;
    private String tel;
    private String port;
    private String mdp;

    public beanMembre() {

    }

    public void createMembre(int id) {
        try {
            System.out.println(">>>>>>>>>>>TEST 1<<<<<<<<<<<<<<<<");
            MaConnexion mc = new MaConnexion();

            String req = "Select * from Membre "
                    + "Where idMembre = " + id + " ";

            PreparedStatement pstm = mc.createStatement(req);
            ResultSet rs = pstm.executeQuery();
            System.out.println(">>>>>>>>>>>>>>>>>>>>> TEST 2 <<<<<<<<<<<<<<<<<<");
            while (rs.next()) {
                this.id = rs.getInt("idMembre");
                this.type = rs.getInt("typeMembre");
                this.nom = rs.getString("nomMembre");
                this.prenom = rs.getString("prenomMembre");
                this.dateNaissance = rs.getString("dateNaissanceMembre");
                this.mail = rs.getString("mailMembre");
                this.tel = rs.getString("telMembre");
                this.port = rs.getString("portMembre");
                this.mdp = rs.getString("mdpMembre");
            }
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<TEST 3<<<<<<<<<<<<");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>><TEST 4 <<<<<<<<<<<<<<<<<<<<<");
        }

    }

    public void SupprMembre() {
        try {
            MaConnexion mc = new MaConnexion();
            //this.id fait reference a l'id contenu dans l'objet qui va executer 
            //la methode dans le controller. Dans notre cas ce sera "m".
            // ATTENTION: si m est null cela provoquera un nullPointer.
            String req = "Update Membre "
                    + "Set actifMembre = 0 "
                    + "Where idMembre = " + this.id + " ";
            PreparedStatement pstm = mc.createStatement(req);
            pstm.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
//    public void Syncho(){
//    this.type = 
//    }

    public void UpdateMembre() {
        try {
            MaConnexion mc = new MaConnexion();
            String req = "Update Membre "
                    + "Set typeMembre = " +this.type
                    + " ,mdpMembre = " + "'"+this.mdp + "'"
                    + " ,nomMembre = " + "'"+this.nom + "'"
                    + " ,prenomMembre = " + "'"+this.prenom + "'"
                    + " ,dateNaissanceMembre = " + "'"+this.dateNaissance +"'"
                    + " ,mailMembre = " + "'" +this.mail + "'"
                    + " ,telMembre = " + "'"+this.tel + "'"
                    + " ,portMembre = " + "'" + this.port + "'"
                    + " Where idMembre = "+this.id;
            
            PreparedStatement pstm = mc.createStatement(req);
            pstm.executeUpdate();
            mc.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
   
    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public String getPort() {
        return port;
    }

    public String getMdp() {
        return mdp;
    }

}
