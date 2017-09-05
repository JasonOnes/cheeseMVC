package org.launchode.cheesemvc.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {

    @NotNull //note check for @Unique annotation
    @Size(min=5, max=15, message="name must be between 5-15 characters!!")
    public String username; //note made public so could use for display in views without calling getUserName

    @Email(message="not a valid email")
    private String email;

    @NotNull(message="Password is not optional!!")
    @Size(min=6, message="Passwords must contain at least 6 characters.")
    private String password;

    @NotNull(message="Passwords do not match!")
    private String verifyPassword;


    public Date dateAdded;


    public User(String username, String email, String password, String verifyPassword, Date dateAdded) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateAdded = dateAdded;
        this.verifyPassword = verifyPassword;
    }

    private int userId;
    private static int nextId = 1;

    public User() {
        userId = nextId;
        nextId++;

    }

    public int getUserId() {
        return userId;
    }
       public void setUserId(int userId) {
        this.userId = userId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }



    public Date getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(Date date) {
        this.dateAdded = date;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }


    public void checkPassword() {
        if (password!=null && verifyPassword!=null && !password.equals(verifyPassword)) {
            verifyPassword = null;
        }
    }
}
