package com.example.mealplanner.model.meal.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Nutrition {
    @SerializedName("nutrients")
    private List<Nutrient> nutrients;
    @SerializedName("properties")
    private List<Propertie>  properties ;
    @SerializedName("flavonoids")
    private List<Propertie> flavonoids;
    @SerializedName("ingredients")
    private  List<IngredientNutrition>   ingredients;
    @SerializedName("caloricBreakdown")
    private CaloricBreakdown caloricBreakdown ;
    @SerializedName("weightPerServing")
    private WeightPerServing         weightPerServing;

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }
}
