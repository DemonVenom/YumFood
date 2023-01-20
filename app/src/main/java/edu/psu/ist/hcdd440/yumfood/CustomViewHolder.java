package edu.psu.ist.hcdd440.yumfood;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title, text_source;
    ImageView img_recipe;
    CardView cardView;
    ShapeableImageView likeImage;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.text_title);
        text_source = itemView.findViewById(R.id.text_source);
        img_recipe = itemView.findViewById(R.id.img_recipe);
        cardView = itemView.findViewById(R.id.main_container);
        likeImage = itemView.findViewById(R.id.imageLike);
    }



}
