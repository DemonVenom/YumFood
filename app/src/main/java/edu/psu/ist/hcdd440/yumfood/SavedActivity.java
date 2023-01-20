package edu.psu.ist.hcdd440.yumfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.hcdd440.yumfood.models.Recipe;
import edu.psu.ist.hcdd440.yumfood.models.RecipeApiResponse;

public class SavedActivity extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;

    ProgressDialog dialog;

    public List<Recipe> recipeSaved, recipeFileSaved;

    Recipe recipe;
    TextView txt_title, txt_author, txt_time, txt_detail, txt_content;
    ImageView img_recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);


        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching saved recipes...");
        dialog.show();


        RequestManager manager = new RequestManager(this);

        manager.getRecipe(listener, "", "1");
    }


    private final OnFetchDataListener<RecipeApiResponse> listener = new OnFetchDataListener<RecipeApiResponse>() {
        @Override
        public void onFetchData(List<Recipe> list, String message) {

            if(list.isEmpty()) {
                Toast.makeText(SavedActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else {

                showRecipes(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {

            Toast.makeText(SavedActivity.this, "An Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void OnRecipeClicked(Recipe recipe) {

        startActivity(new Intent(SavedActivity.this, DetailsActivity.class)
                .putExtra("data", recipe));
    }

    @Override
    public void OnRecipeSaved(Recipe recipe) {

    }

    private void showRecipes(List<Recipe> list) {

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        recipeSaved = (List<Recipe>) getIntent().getSerializableExtra("data");

        adapter = new CustomAdapter(this, recipeSaved, this);
        recyclerView.setAdapter(adapter);

    }


}