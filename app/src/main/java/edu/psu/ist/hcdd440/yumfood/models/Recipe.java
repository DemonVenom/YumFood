package edu.psu.ist.hcdd440.yumfood.models;

import java.io.Serializable;

public class Recipe implements Serializable {

    String id = "";

    String sourceName = "";
    String title = "";
    String summary = "";
    String sourceURL = "";
    String image = "";
    String imageType = "";
    String instructions = "";

    Ingredient[] extendedIngredients = null;

    String[] diets = null;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Ingredient[] getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(Ingredient[] extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public String[] getDiets() {
        return diets;
    }

    public void setDiets(String[] diets) {
        this.diets = diets;
    }
}
