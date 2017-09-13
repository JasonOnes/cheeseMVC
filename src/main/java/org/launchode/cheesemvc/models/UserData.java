package org.launchode.cheesemvc.models;

import java.util.ArrayList;


import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();
    private static UserData instance;

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
    //add
    public static void add(User newUser) {
        users.add(newUser);
    }

    //getAll
    public static ArrayList<User> getAll() {
        return users;
    }

    //getById
    public static User getById(int id) {
        //User aUser = null;

        for (User someUser : users) {
            if (someUser.getUserId() == id)
            return someUser;
        }
        return null;
    }
    //delete
    public static void delete(User someLoser) {
        users.remove(someLoser);
    }
}
