package com.mbelisaire.familysecret;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.mbelisaire.familysecret.dao.Recipe;
import com.mbelisaire.familysecret.dao.RecipesFactory;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeStepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeStepListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private Recipe recipe;

    /**
     * The argument representing the item ID that this activity
     * represents.
     */
    public static final String ARG_RECIPE_ID = "recipe_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        if (savedInstanceState != null) {
            recipe = getRecipe(savedInstanceState.getInt(AppConstants.CACHED_RECIPE_ID_KEY));
        } else {
                // Create the detail fragment and add it to the activity
                // using a fragment transaction.
                Bundle arguments = new Bundle();
                arguments.putInt(ARG_RECIPE_ID,
                        getIntent().getIntExtra(ARG_RECIPE_ID, -1));
                recipe = getRecipe(arguments.getInt(ARG_RECIPE_ID));
        }

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(recipe.getName());
        }
        RecyclerView stepListRecycler = findViewById(R.id.item_list);
        RecyclerView ingredientListRecycler = findViewById(R.id.ingredient_list);
        setupRecyclerViews(stepListRecycler, ingredientListRecycler);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(AppConstants.CACHED_RECIPE_ID_KEY, recipe.getId());
        super.onSaveInstanceState(outState);
    }

    private Recipe getRecipe(int itemId) {
        return RecipesFactory.ITEM_MAP.get(itemId);
    }

    private void setupRecyclerViews(@NonNull RecyclerView stepRecyclerView, @NonNull RecyclerView ingredientRecyclerView) {
        stepRecyclerView.setAdapter(new RecipeRecyclerViewAdapter(this, recipe, mTwoPane));
        ingredientRecyclerView.setAdapter(new IngredientListRecyclerViewAdapter(recipe.getIngredients()));
    }
}