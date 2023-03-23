package presentation;

import java.io.Serializable;

/**
 * Clasa reprezinta clasa principala pentru fiecare tip de user
 */
public class User implements Serializable {

    private final String username;
    private final String password;

    private final int id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

        id = (username + password).hashCode();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

}
