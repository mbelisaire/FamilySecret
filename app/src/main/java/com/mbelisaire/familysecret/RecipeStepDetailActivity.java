package com.mbelisaire.familysecret;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.NavUtils;

import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mbelisaire.familysecret.dao.Recipe;
import com.mbelisaire.familysecret.dao.RecipesFactory;
import com.mbelisaire.familysecret.dao.Step;

import java.util.ArrayList;

import static com.mbelisaire.familysecret.RecipeStepDetailFragment.ARG_STEP_ID;
import static com.mbelisaire.familysecret.RecipeStepListActivity.ARG_RECIPE_ID;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeStepListActivity}.
 */
public class RecipeStepDetailActivity extends AppCompatActivity {

    private int recipeId;
    private int stepId;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don"t need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            recipeId = getIntent().getIntExtra(ARG_RECIPE_ID, -1);
            stepId = getIntent().getIntExtra(ARG_STEP_ID, -1);
        } else {
            recipeId = savedInstanceState.getInt(AppConstants.CACHED_RECIPE_ID_KEY);
            stepId = savedInstanceState.getInt(AppConstants.CACHED_STEP_ID_KEY);
        }
        // Create the detail fragment and add it to the activity
        // using a fragment transaction.
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_RECIPE_ID, recipeId);
        arguments.putInt(ARG_STEP_ID, stepId);
        recipe = RecipesFactory.ITEM_MAP.get(arguments.getInt(ARG_RECIPE_ID));
        if(recipe != null) {
            if (actionBar != null) {
                actionBar.setTitle(recipe.getName());
            }
        }

        RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit();

        FloatingActionButton nextStepButton = findViewById(R.id.floatingNextStepButton);
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextStepId = getNextStepId();
                final Intent intent = new Intent(getApplicationContext(), RecipeStepDetailActivity.class);
                intent.putExtra(ARG_RECIPE_ID, recipeId);
                intent.putExtra(ARG_STEP_ID, nextStepId);
                startActivity(intent);
            }
        });

        FloatingActionButton previousStepButton = findViewById(R.id.floatingPreviousStepButton);
        previousStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousStepId = getPreviousStepId();
                final Intent intent = new Intent(getApplicationContext(), RecipeStepDetailActivity.class);
                intent.putExtra(ARG_RECIPE_ID, recipeId);
                intent.putExtra(ARG_STEP_ID, previousStepId);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, RecipeStepListActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(AppConstants.CACHED_RECIPE_ID_KEY, recipeId);
        outState.putInt(AppConstants.CACHED_STEP_ID_KEY, stepId);
        super.onSaveInstanceState(outState);
    }

    private int getNextStepId() {
        int nextStepId = stepId + 1;
        if(!recipe.stepHashMap.containsKey(nextStepId)) {
            nextStepId = 0;
        }
        return nextStepId;
    }

    private int getPreviousStepId(){
        int previousStepId = stepId - 1;
        if(!recipe.stepHashMap.containsKey(previousStepId)) {
            ArrayList<Step> steps = recipe.getSteps();
            previousStepId = steps.get(steps.size() - 1).getId();
        }
        return previousStepId;
    }
}