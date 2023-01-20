package edu.psu.ist.hcdd440.yumfood;

import edu.psu.ist.hcdd440.yumfood.models.Recipe;

public interface SelectListener {

    void OnRecipeClicked(Recipe recipe);

    void OnRecipeSaved(Recipe recipe);


}
