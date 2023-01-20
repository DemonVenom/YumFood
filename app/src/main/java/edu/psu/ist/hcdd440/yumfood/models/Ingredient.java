package edu.psu.ist.hcdd440.yumfood.models;

import java.io.Serializable;

public class Ingredient implements Serializable {

    String id = "";

    String original = "";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
