package com.mbelisaire.familysecret;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelisaire.familysecret.dao.Recipe;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecipesListRecyclerViewAdapter extends RecyclerView.Adapter<RecipesListRecyclerViewAdapter.ViewHolder>  {

    private final MainActivity mParentActivity;
    private final List<Recipe> mValues;

    RecipesListRecyclerViewAdapter(MainActivity parent,
                                   List<Recipe> items) {
        mValues = items;
        mParentActivity = parent;
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Recipe item = (Recipe) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, RecipeStepListActivity.class);
            intent.putExtra(RecipeStepListActivity.ARG_RECIPE_ID, item.getId());

            context.startActivity(intent);
        }
    };

    @Override
    public RecipesListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_content, parent, false);
        return new RecipesListRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipesListRecyclerViewAdapter.ViewHolder holder, int position) {
        //TODO Use library to bind or to show images
        holder.mServings.setText(String.valueOf(String.valueOf(mValues.get(position).getServings())));
        holder.mRecipeName.setText(mValues.get(position).getName());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mServings;
        final TextView mRecipeName;

        ViewHolder(View view) {
            super(view);
            mServings = (TextView) view.findViewById(R.id.servings);
            mRecipeName = (TextView) view.findViewById(R.id.recipe_name);
        }
    }
}
