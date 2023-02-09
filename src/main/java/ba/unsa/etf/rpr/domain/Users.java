package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * @author Kadric Nerma
 * List of users of company
 */
public class Users implements Idable {
    private int ID;
    private String username;
    private String pass;

    public Users() {
    }

    public Users(int ID, String username, String pass) {
        this.ID = ID;
        this.username = username;
        this.pass = pass;
    }

    public Users(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public void setId(int id) {
        this.ID = id;
    }


    public int getId() {
        return ID;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return ID == users.ID && username.equals(users.username) && pass.equals(users.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, username, pass);
    }

    @Override
    public String toString() {
        return "Users{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
