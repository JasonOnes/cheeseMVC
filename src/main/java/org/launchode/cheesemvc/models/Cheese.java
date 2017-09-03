package org.launchode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.soap.Text;

public class Cheese {

    @NotNull
    @Size(min=3, max=15, message = "between three and fifteen letters")
    private String name;
    @NotNull //note if form field not filled out returns not null but an empty string ""
    @Size(min=1, message = "Hey type something in here  you Dolt!")//note cont. thus add requirement that string must
    //note cont. contain at least one character
    private String description;

    @NotNull
    private CheeseType type;


    private int cheeseId;
    private static int nextId = 0;

    //note try with ---public Text description;
    public Cheese(String name, String description, CheeseType type) {
        this();//calss default constructor (below) to initialize cheeseId field
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Cheese() {
        cheeseId = nextId;
         nextId++;
    }

    public int getCheeseId(){
        return cheeseId;
    }
    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getName() {
        return name;
    }
    public void setName(String aName) {
        name = aName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String aDescripto) {
        description = aDescripto;
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}