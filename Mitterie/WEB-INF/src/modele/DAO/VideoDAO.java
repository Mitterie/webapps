package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modele.POJO.Video;

public class VideoDAO {

    public static Video find(String url) {
        Video res = null;
        Connection con = ConnectionDAO.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM videosmitterie where url = ?;");
            pstmt.setString(1, url);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = new Video(rs.getString(1), rs.getString(2), LocalDate.parse(rs.getString(3)),
                        LocalTime.parse(rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static List<Video> findAll() {
        List<Video> res = new ArrayList<Video>();
        Connection con = ConnectionDAO.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM videosmitterie;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                res.add(new Video(rs.getString(1), rs.getString(2), LocalDate.parse(rs.getString(3)),
                        LocalTime.parse(rs.getString(4))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static List<Video> find3Last() {
        List<Video> inverse = new ArrayList<Video>();
        Connection con = ConnectionDAO.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT * FROM videosmitterie LIMIT 3 OFFSET (SELECT COUNT(*) - 3 FROM videosmitterie);");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                inverse.add(new Video(rs.getString(1), rs.getString(2), LocalDate.parse(rs.getString(3)),
                        LocalTime.parse(rs.getString(4))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Video> res = new ArrayList<Video>();
        for (int i = inverse.size() - 1; i >= 0; i--) {
            res.add(inverse.get(i));
        }

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static Video findRandom() {
        Video res = null;
        Connection con = ConnectionDAO.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM videosmitterie ORDER BY RANDOM() LIMIT 1;");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = new Video(rs.getString(1), rs.getString(2), LocalDate.parse(rs.getString(3)),
                        LocalTime.parse(rs.getString(4)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static boolean createVideo(Video v) {
        boolean res = false;
        Connection con = ConnectionDAO.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO videosmitterie VALUES (?,?,'"
                    + LocalDate.now().toString() + "','" + LocalTime.now().toString() + "');");
            stmt.setString(1, v.getUrl());
            stmt.setString(2, v.getTitle());
            int r = stmt.executeUpdate();
            if (r != -1) {
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate("NOTIFY bot, '" + v.getTitle() + "';");
                res = true;
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static int findNbVideo(String rech) {
        int res = 0;
        Connection con = ConnectionDAO.getConnection();
        try {
            ResultSet rs;
            Statement stmt = con.createStatement();
            if (rech.equals("")) {
                rs = stmt.executeQuery("SELECT COUNT (*) FROM videosmitterie;");
            } else {
                rs = stmt.executeQuery(
                        "SELECT COUNT (*) FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%" + rech + "%');");
            }
            if (rs.next()) {
                res = rs.getInt(1);
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static List<Video> findAllWith(String rech, boolean croissant) {
        List<Video> res = new ArrayList<Video>();
        Connection con = ConnectionDAO.getConnection();
        try {
            ResultSet rs;
            Statement stmt = con.createStatement();
            if (croissant) {
                if (rech.equals("")) {
                    rs = stmt.executeQuery("SELECT * FROM videosmitterie ORDER BY datesortie DESC, heuresortie DESC;");
                } else {
                    rs = stmt.executeQuery("SELECT * FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%" + rech + "%') ORDER BY datesortie DESC, heuresortie DESC;");
                }
            } else {
                if (rech.equals("")) {
                    rs = stmt.executeQuery("SELECT * FROM videosmitterie ORDER BY datesortie , heuresortie;");
                } else {
                    rs = stmt.executeQuery("SELECT * FROM videosmitterie WHERE UPPER(titre) LIKE UPPER('%" + rech + "%') ORDER BY datesortie , heuresortie;");
                }
            }
            while(rs.next()){
                res.add(new Video(rs.getString(1), rs.getString(2), LocalDate.parse(rs.getString(3)),LocalTime.parse(rs.getString(4))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
