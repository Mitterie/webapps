package modele.POJO;

import java.time.LocalDate;
import java.time.LocalTime;

public class User {

    private int id;
    private String name;
    private String password;
    private String role;
    private String email;
    private LocalDate lastDayCo;
    private LocalTime lastTimeCo;

    public User(int id, String name, String password, String role, String email, LocalDate lastDayCo, LocalTime lastTimeCo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.email = email;
        this.lastDayCo = lastDayCo;
        this.lastTimeCo = lastTimeCo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getLastDayCo() {
        return lastDayCo;
    }

    public LocalTime getLastTimeCo() {
        return lastTimeCo;
    }
}
