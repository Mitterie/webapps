package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class SecurityDAO {

    public static boolean isUserValid(String login, String password) {
        Connection con = ConnectionDAO.getConnection();
        boolean res = false;
        try {
            // exécution de la requete
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM loginmitterie WHERE login = ? AND mdp = MD5(?);");
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = true;
                Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE loginmitterie SET dayLastCo = '" + LocalDate.now().toString()+ "', hourLastCo = '" + LocalTime.now().toString() + "' WHERE login = '" + login + "';");
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
        try{
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
}
