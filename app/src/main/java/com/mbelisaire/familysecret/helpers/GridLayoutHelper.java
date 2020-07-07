package com.mbelisaire.familysecret.helpers;

import android.content.Context;
import android.util.DisplayMetrics;

public final class GridLayoutHelper {
    public static int calculateNumberOfColumns(Context context, float columnWidth) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int numberOfColumns = (int) (screenWidthDp / columnWidth + 0.5); // +0.5 for correct rounding to int.
        return numberOfColumns;
    }
}
