package com.mbelisaire.familysecret.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class RecipesFactory {

    /**
     * An array of sample recipe items.
     */
    public static final List<Recipe> ITEMS = new ArrayList<>();

    /**
     * A map of the recipe items, by ID.
     */
    public static final Map<Integer, Recipe> ITEM_MAP = new HashMap<>();

    private static void addItem(Recipe item) {
        item.createStepMap();
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

     public static void addItems(ArrayList<Recipe> items) {
         for (Recipe i : items) addItem(i);
    }
}