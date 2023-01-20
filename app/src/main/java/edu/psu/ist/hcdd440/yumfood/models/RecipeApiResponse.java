package edu.psu.ist.hcdd440.yumfood.models;

import java.io.Serializable;
import java.util.List;

public class RecipeApiResponse implements Serializable {

    List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
