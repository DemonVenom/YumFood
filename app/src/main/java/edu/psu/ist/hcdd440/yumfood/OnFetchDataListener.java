package edu.psu.ist.hcdd440.yumfood;

import java.util.List;

import edu.psu.ist.hcdd440.yumfood.models.Recipe;

public interface OnFetchDataListener<RecipeApiResponse> {

    void onFetchData(List<Recipe> list, String message);
    void onError(String message);


}
