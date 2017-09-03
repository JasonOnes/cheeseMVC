package org.launchode.cheesemvc.models;

import java.util.ArrayList;

public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    //getAll
    public static ArrayList<Cheese> getAll()  {
        return cheeses;
    }
    // add
    public static void add(Cheese newCheese) {
        cheeses.add(newCheese);
    }

    // delete
    public static void delete(int id) {
        Cheese cheeseToRemove = getById(id);
        cheeses.remove(cheeseToRemove);
    }


    // getById
    public static Cheese getById(int id) {

        Cheese theCheese = null;

        for (Cheese someCheese : cheeses) {
            if (someCheese.getCheeseId() == id) {
                theCheese = someCheese;
            }
        }
        return theCheese;
    }
}
