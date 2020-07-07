package com.mbelisaire.familysecret.dao;

import java.math.BigDecimal;

import androidx.annotation.NonNull;

public final class Ingredient {
    private final Double quantity;
    private final String measure;
    private final String ingredient;


    public Ingredient(Double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @NonNull
    @Override
    public String toString() {
        BigDecimal decimalQuantity = new BigDecimal(quantity);
        String result = ingredient + ": " + decimalQuantity.stripTrailingZeros().toPlainString() + " " + measure;
        return "- " + result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
    }
}