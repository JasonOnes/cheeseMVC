package org.launchode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @NotNull //note check for @Unique annotation
    @Size(min=2, max=15, message="name must be between 5-15 characters!!")
    public String username; //note made public so could use for display in views without calling getUserName
    @NotNull
    @Size(min=5, max=25, message="email must be between 5-25")
    private String email;

    @NotNull
    @Size(min=1, message="Password in not optional!!")
    private String password;

//    @NotNull
//    @Size(min=1)
    //private String passwordVerify;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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
    }

//    public String getPasswordVerify() {
//        return passwordVerify;
//    }
//
//    public void setPasswordVerify(String passwordVerify) {
//        this.passwordVerify = passwordVerify;
//    }

}
