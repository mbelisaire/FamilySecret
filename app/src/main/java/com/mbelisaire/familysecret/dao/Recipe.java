package com.mbelisaire.familysecret.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  class Recipe {
    private final int id;
    private final String name;
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<Step> steps;
    private final int servings;

    public ArrayList<Step> getSteps() {
        return steps;
    }

    private final String image;
    private final String details;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, int servings, String image, String details) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
        this.details = details;
        createStepMap();
    }

    public void createStepMap() {
        stepHashMap = new HashMap<>();
        for (Step step:steps) {
            stepHashMap.put(step.getId(), step);
        }
    }

    /**
     * A map of the step items, by ID.
     */
    public Map<Integer, Step> stepHashMap = new HashMap<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return name;
    }
}