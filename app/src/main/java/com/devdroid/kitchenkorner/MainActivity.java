package com.devdroid.kitchenkorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.devdroid.kitchenkorner.Models.Recipe;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Recipe> recipeList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //Set up RecyclerView
       /* RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList.add(new Recipe("Pasta ", "Ingredients.. \n1.", "Instructions...", R.drawable.pasta));
        recipeList.add(new Recipe("Pasta ", "Ingredients.. \n1.", "Instructions...", R.drawable.pasta));
       RecipeAdapter adapter=new RecipeAdapter(this,recipeList);
        recyclerView.setAdapter(adapter);*/


        FirebaseApp.initializeApp(this);
        DatabaseReference databaseRef= FirebaseDatabase.getInstance().getReference("recipes");

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(new Recipe("pasta","Instruction","Ingredients",R.drawable.pasta));
        recipeList.add(new Recipe("pasta","Instruction","Ingredients",R.drawable.pasta));
        recipeList.add(new Recipe("pasta","Instruction","Ingredients",R.drawable.pasta));

        for (Recipe recipe : recipeList) {
            String key = databaseRef.push().getKey();
            databaseRef.child(key).setValue(recipe);
        }



       /* String recipeId = recipesRef.push().getKey(); // Generate a unique ID for the recipe
        recipesRef.child(recipeId).setValue(recipe);*/



        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Recipe> recipeList = new ArrayList<>();

                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = recipeSnapshot.getValue(Recipe.class);
                    recipeList.add(recipe);
                }

                // Handle the retrieved recipeList here
                // You can pass it to an adapter for display or perform any other operations

// Add your Recipe objects to the recipeList

                RecyclerView recyclerView = findViewById(R.id.recipeRecyclerView);
                RecipeAdapter adapter = new RecipeAdapter(recipeList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any error occurred while retrieving data
                Log.e("FirebaseError", "Error retrieving data: " + databaseError.getMessage());
            }
        };

        databaseRef.addListenerForSingleValueEvent(valueEventListener);


    }
    }
