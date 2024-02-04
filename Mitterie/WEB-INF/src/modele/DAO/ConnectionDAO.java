package modele.DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDAO {

    public static Connection getConnection() {
        Connection res = null;
        try {
            Properties pr = new Properties();
            pr.load(new FileInputStream("/opt/tomcat/postgres.prop"));
            Class.forName(pr.getProperty("driver"));
            String url = pr.getProperty("url");
            String nom = pr.getProperty("nom");
            String mdp = pr.getProperty("mdp");
            res = DriverManager.getConnection(url, nom, mdp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
