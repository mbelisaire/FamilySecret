package com.mbelisaire.familysecret;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelisaire.familysecret.dao.Recipe;
import com.mbelisaire.familysecret.dao.Step;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.mbelisaire.familysecret.RecipeStepDetailFragment.ARG_STEP_ID;
import static com.mbelisaire.familysecret.RecipeStepListActivity.ARG_RECIPE_ID;

public final class RecipeRecyclerViewAdapter
        extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Step item = (Step) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putInt(ARG_RECIPE_ID, recipeId);
                arguments.putInt(ARG_STEP_ID, item.getId());
                RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeStepDetailActivity.class);
                intent.putExtra(ARG_RECIPE_ID, recipeId);
                intent.putExtra(ARG_STEP_ID, item.getId());

                context.startActivity(intent);
            }
        }
    };
    private final RecipeStepListActivity mParentActivity;
    private final List<Step> mValues;
    private final boolean mTwoPane;
    private final int recipeId;


    RecipeRecyclerViewAdapter(RecipeStepListActivity parent,
                              Recipe recipe,
                              boolean twoPane) {
        recipeId = recipe.getId();
        mValues = recipe.getSteps();
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mShortDescription.setText(mValues.get(position).getShortDescription());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mShortDescription;

        ViewHolder(View view) {
            super(view);
            mShortDescription = (TextView) view.findViewById(R.id.stepShortDescription);
        }
    }
}
