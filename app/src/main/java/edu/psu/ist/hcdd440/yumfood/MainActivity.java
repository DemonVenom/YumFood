package edu.psu.ist.hcdd440.yumfood;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.psu.ist.hcdd440.yumfood.models.Recipe;
import edu.psu.ist.hcdd440.yumfood.models.RecipeApiResponse;


public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
    SearchView searchView;

    Recipe firstRecipe;

    AlertDialog.Builder builder;

    List<Recipe> recipeSaved;

    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.search_view);

        recipeSaved = new ArrayList<Recipe>();

        //Create the 'Liked' recipe button and set listener
        ShapeableImageView button = findViewById(R.id.imageLike);
        button.setOnClickListener(this);

        //Create the 'Search' recipe button and set listener
        button = findViewById(R.id.imageHome);
        button.setOnClickListener(this);

        //Create the 'Settings' button and listener
        button = findViewById(R.id.imageSettings);
        button.setOnClickListener(this);


        swipeRefreshLayout = findViewById(R.id.recycler_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });


        builder = new AlertDialog.Builder(this);
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog.setTitle("Fetching recipes of " + query);
                dialog.show();

                RequestManager manager = new RequestManager(MainActivity.this);
                // manager.getRecipe(listener);

                manager.getRecipe(listener, query, "3");
                // TODO: REPLACE 1 WITH OTHER NUMBER


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching recipes...");
        dialog.show();

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);
        b8 = findViewById(R.id.btn_8);
        b8.setOnClickListener(this);
        b9 = findViewById(R.id.btn_9);
        b9.setOnClickListener(this);
        b10 = findViewById(R.id.btn_10);
        b10.setOnClickListener(this);
        b11 = findViewById(R.id.btn_11);
        b11.setOnClickListener(this);


        RequestManager manager = new RequestManager(this);
        // manager.getRecipe(listener);

        manager.getRecipe(listener, "", "3");
        // TODO: REPLACE 1 WITH OTHER NUMBER
    }

    private final OnFetchDataListener<RecipeApiResponse> listener = new OnFetchDataListener<RecipeApiResponse>() {
        @Override
        public void onFetchData(List<Recipe> list, String message) {

            if(list.isEmpty()) {
                dialog.dismiss();

                // Toast.makeText(MainActivity.this, "No data found!!!", Toast.LENGTH_SHORT).show();

                AlertDialog alert = builder.create();
                alert.setTitle("No recipes found!");
                alert.setMessage("Try using other keywords or search for a different recipe");
                alert.show();


            }
            else {

                showRecipes(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {

//            Toast.makeText(MainActivity.this, "An Error Occurred!!!", Toast.LENGTH_SHORT).show();

            AlertDialog alert = builder.create();
            alert.setTitle("Error!");
            alert.setTitle("No data  found");
            alert.show();

        }
    };

    private void showRecipes(List<Recipe> list) {

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);

        firstRecipe = list.get(0);
    }

    @Override
    public void OnRecipeClicked(Recipe recipe) {

        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                .putExtra("data", recipe));
    }



    @Override
    public void OnRecipeSaved(Recipe recipe) {

        recipeSaved.add(recipe);

//        startActivity(new Intent(MainActivity.this, SavedActivity.class)
//                .putExtra("data", recipe));
    }




    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.imageLike) {
            handleLike();
        }
        else if (v.getId() == R.id.imageHome) {
            handleHome();
        }
        else if (v.getId() == R.id.imageSettings) {
            handleSettings();
        }
        else {

            Button button = (Button) v;
            String tag = button.getText().toString();

            dialog.setTitle("Fetching recipes of " + tag);
            dialog.show();

            RequestManager manager = new RequestManager(this);
            // manager.getRecipe(listener);

            manager.getRecipe(listener, tag, "3");
            // TODO: REPLACE 1 WITH OTHER NUMBER
        }


    }


    private void handleLike() {

        // Create new intent on SettingsActivity class
        Intent intent = new Intent(this, SavedActivity.class).putExtra("data", (Serializable) recipeSaved);
        startActivity(intent);

    }

    private void handleSettings() {

        // Create new intent on SettingsActivity class
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }


    private void handleHome() {

        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }




}