package edu.psu.ist.hcdd440.yumfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.psu.ist.hcdd440.yumfood.models.Recipe;


public class CustomAdapter extends RecyclerView.Adapter<edu.psu.ist.hcdd440.yumfood.CustomViewHolder> {

    private Context context;
    private List<Recipe> recipes;
    private edu.psu.ist.hcdd440.yumfood.SelectListener listener;

    public CustomAdapter(Context context, List<Recipe> recipes, edu.psu.ist.hcdd440.yumfood.SelectListener listener) {
        this.context = context;
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public edu.psu.ist.hcdd440.yumfood.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new edu.psu.ist.hcdd440.yumfood.CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull edu.psu.ist.hcdd440.yumfood.CustomViewHolder holder, int position) {

        holder.text_title.setText(recipes.get(position).getTitle());

        holder.text_source.setText(recipes.get(position).getSourceName());

        if (recipes.get(position).getImage()!=null) {
            Picasso.get().load(recipes.get(position).getImage()).into(holder.img_recipe);
            holder.img_recipe.setContentDescription(recipes.get(position).getTitle());
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnRecipeClicked(recipes.get(position));
            }
        });

        holder.likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnRecipeSaved(recipes.get(position));

                holder.likeImage.setBackgroundColor(ContextCompat.getColor(context, R.color.darker_gray));

            }
        });


    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
