package edu.psu.ist.hcdd440.yumfood;

import android.content.Context;
import android.widget.Toast;

import edu.psu.ist.hcdd440.yumfood.models.RecipeApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {

    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getRecipe(OnFetchDataListener listener, String tags, String number) {

        CallRecipeApi callRecipeApi = retrofit.create(CallRecipeApi.class);
        Call<RecipeApiResponse> call = callRecipeApi.callRecipes(tags, number, context.getString(R.string.api_key));
        try {
            call.enqueue(new Callback<RecipeApiResponse>() {
                @Override
                public void onResponse(Call<RecipeApiResponse> call, Response<RecipeApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getRecipes(), response.message());
                }

                @Override
                public void onFailure(Call<RecipeApiResponse> call, Throwable t) {

                    listener.onError("Request Failed");

                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getRecipeSearch(OnFetchDataListener listener, String query, String number) {

        CallRecipeSearchApi callRecipeSearchApi = retrofit.create(CallRecipeSearchApi.class);
        Call<RecipeApiResponse> call = callRecipeSearchApi.callRecipes(query, number, context.getString(R.string.api_key));
        try {
            call.enqueue(new Callback<RecipeApiResponse>() {
                @Override
                public void onResponse(Call<RecipeApiResponse> call, Response<RecipeApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getRecipes(), response.message());
                }

                @Override
                public void onFailure(Call<RecipeApiResponse> call, Throwable t) {

                    listener.onError("Request Failed");

                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public RequestManager(Context context) {
        this.context = context;
    }

    public interface CallRecipeApi {
        @GET("random")
        Call<RecipeApiResponse> callRecipes(
                @Query("tags") String tags,
                @Query("number") String number,
                @Query("apiKey") String api_key
        );
    }

    public interface CallRecipeSearchApi {
        @GET("complexSearch")
        Call<RecipeApiResponse> callRecipes(
                @Query("query") String query,
                @Query("number") String number,
                @Query("apiKey") String api_key
        );
    }

}
