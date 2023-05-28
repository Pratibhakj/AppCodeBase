package com.devdroid.kitchenkorner;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devdroid.kitchenkorner.Models.Recipe;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
         Context context;

        ArrayList<Recipe> recipeList;

        RecipeAdapter(ArrayList<Recipe> recipeList) {
            this.recipeList = recipeList;
        }

        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
            return new RecipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            Recipe recipe = recipeList.get(position);
            holder.titleTextView.setText(recipe.getTitle());
            holder.ingredientsTextView.setText(recipe.getIngredients());
            holder.recipeImageView.setImageResource(recipe.getImageResId());
           holder.instructionsTextView.setText(recipe.getInstructions());


        }

        @Override
        public int getItemCount() {
            return recipeList.size();
        }

        public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView titleTextView;
            public TextView ingredientsTextView;
            public ImageView recipeImageView;
            public TextView instructionsTextView;

            public RecipeViewHolder(View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                ingredientsTextView = itemView.findViewById(R.id.ingredientsTextView);
                recipeImageView = itemView.findViewById(R.id.recipeImageView);
                itemView.setOnClickListener(this);
                instructionsTextView=itemView.findViewById(R.id.instructionsTextView);
            }
            public void onClick(View view){
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    Recipe selectedRecipe = recipeList.get(position);
                    Intent intent = new Intent(view.getContext(),Recept_details.class);

                    view.getContext().startActivity(intent);
                }

            }

        }
    }


