package com.example.mealplanner.model.meal;

import com.example.mealplanner.model.meal.details.AnalyzedInstruction;
import com.example.mealplanner.model.meal.details.ExtendedIngredient;
import com.example.mealplanner.model.meal.details.WinePairing;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeDetails {
    @SerializedName("vegetarian")
    private boolean vegetarian;

    @SerializedName("vegan")
    private boolean vegan;

    @SerializedName("glutenFree")
    private boolean glutenFree;

    @SerializedName("dairyFree")
    private boolean dairyFree;

    @SerializedName("veryHealthy")
    private boolean veryHealthy;

    @SerializedName("cheap")
    private boolean cheap;

    @SerializedName("veryPopular")
    private boolean veryPopular;

    @SerializedName("sustainable")
    private boolean sustainable;

    @SerializedName("lowFodmap")
    private boolean lowFodmap;

    @SerializedName("weightWatcherSmartPoints")
    private int weightWatcherSmartPoints;

    @SerializedName("gaps")
    private String gaps;

    @SerializedName("preparationMinutes")
    private int preparationMinutes;

    @SerializedName("cookingMinutes")
    private int cookingMinutes;

    @SerializedName("aggregateLikes")
    private int aggregateLikes;

    @SerializedName("healthScore")
    private int healthScore;

    @SerializedName("creditsText")
    private String creditsText;

    @SerializedName("license")
    private String license;

    @SerializedName("sourceName")
    private String sourceName;

    @SerializedName("pricePerServing")
    private double pricePerServing;

    @SerializedName("extendedIngredients")
    private List<ExtendedIngredient> extendedIngredients;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("readyInMinutes")
    private int readyInMinutes;

    @SerializedName("servings")
    private int servings;

    @SerializedName("sourceUrl")
    private String sourceUrl;

    @SerializedName("image")
    private String image;

    @SerializedName("imageType")
    private String imageType;

    @SerializedName("summary")
    private String summary;

    @SerializedName("cuisines")
    private List<String> cuisines;

    @SerializedName("dishTypes")
    private List<String> dishTypes;

    @SerializedName("diets")
    private List<String> diets;

    @SerializedName("occasions")
    private List<String> occasions;

    @SerializedName("winePairing")
    private WinePairing winePairing;

    @SerializedName("instructions")
    private String instructions;

    @SerializedName("analyzedInstructions")
    private List<AnalyzedInstruction> analyzedInstructions;

    @SerializedName("originalId")
    private Object originalId;

    @SerializedName("spoonacularScore")
    private double spoonacularScore;

    @SerializedName("spoonacularSourceUrl")
    private String spoonacularSourceUrl;

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public boolean isLowFodmap() {
        return lowFodmap;
    }

    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public String getGaps() {
        return gaps;
    }

    public int getPreparationMinutes() {
        return preparationMinutes;
    }

    public int getCookingMinutes() {
        return cookingMinutes;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public String getCreditsText() {
        return creditsText;
    }

    public String getLicense() {
        return license;
    }

    public String getSourceName() {
        return sourceName;
    }

    public double getPricePerServing() {
        return pricePerServing;
    }

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public List<String> getDishTypes() {
        return dishTypes;
    }

    public List<String> getDiets() {
        return diets;
    }

    public List<String> getOccasions() {
        return occasions;
    }

    public WinePairing getWinePairing() {
        return winePairing;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public Object getOriginalId() {
        return originalId;
    }

    public double getSpoonacularScore() {
        return spoonacularScore;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }


    public void setImage(String image) {
        this.image = image;
    }
}
