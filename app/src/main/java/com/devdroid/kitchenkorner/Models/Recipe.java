package com.devdroid.kitchenkorner.Models;

public class Recipe {
    public String title;
     public String ingredients;
     public String instructions;
     public int imageResId;

    public Recipe(String title, String ingredients, String instructions,int imageResId) {
                this.title = title;
                this.ingredients = ingredients;
                this.instructions = instructions;
                this.imageResId=imageResId;

    }

    // Getter methods
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title=title;

    }

    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }



    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public Recipe() {
        // Required default constructor for Firebase
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}

