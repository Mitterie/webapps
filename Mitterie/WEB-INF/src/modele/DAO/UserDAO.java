package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modele.POJO.User;

public class UserDAO {

    public static User find(String login){
        Connection con = ConnectionDAO.getConnection();
        User res = null;
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM loginmitterie where login = ?;");
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                res = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), LocalDate.parse(rs.getString(6)), LocalTime.parse(rs.getString(7)));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static List<User> findAll(){
        Connection con = ConnectionDAO.getConnection();
        ArrayList<User> res = new ArrayList<User>();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM loginmitterie;");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                res.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), LocalDate.parse(rs.getString(6)), LocalTime.parse(rs.getString(7))));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static List<User> findAllById(){
        Connection con = ConnectionDAO.getConnection();
        ArrayList<User> res = new ArrayList<User>();
        try{
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM loginmitterie ORDER by id;");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                res.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5), LocalDate.parse(rs.getString(6)), LocalTime.parse(rs.getString(7))));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static boolean createUser(User u){
        boolean res = false;
        Connection con = ConnectionDAO.getConnection();

        try{
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO loginmitterie(login,mdp,role,email) VALUES(?,MD5(?),'utils',?);");
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getEmail());
            if(pstmt.executeUpdate() != -1){
                res = true;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
}
