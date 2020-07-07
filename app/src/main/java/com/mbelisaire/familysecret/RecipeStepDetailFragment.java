package com.mbelisaire.familysecret;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelisaire.familysecret.dao.Recipe;
import com.mbelisaire.familysecret.dao.RecipesFactory;
import com.mbelisaire.familysecret.dao.Step;

import static com.mbelisaire.familysecret.RecipeStepListActivity.ARG_RECIPE_ID;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link RecipeStepListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeStepDetailActivity}
 * on handsets.
 */
public class RecipeStepDetailFragment extends Fragment {
    /**
     * The fragment argument representing the step ID that this fragment
     * represents.
     */
    public static final String ARG_STEP_ID = "step_id";

    /**
     * The recipe which includes the step represented by this fragment.
     */
    private Recipe recipe;

    /**
     * The step represented by this fragment.
     */
    private Step step;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeStepDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_RECIPE_ID) && getArguments().containsKey(ARG_STEP_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            recipe = RecipesFactory.ITEM_MAP.get(getArguments().getInt(ARG_RECIPE_ID));
            if (recipe != null) {
                step = recipe.stepHashMap.get(getArguments().getInt(ARG_STEP_ID));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (step != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(step.getShortDescription());
        }

        return rootView;
    }
}