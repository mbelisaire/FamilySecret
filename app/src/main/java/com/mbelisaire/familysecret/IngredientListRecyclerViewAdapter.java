package com.mbelisaire.familysecret;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mbelisaire.familysecret.dao.Ingredient;
import com.mbelisaire.familysecret.dao.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientListRecyclerViewAdapter extends RecyclerView.Adapter<IngredientListRecyclerViewAdapter.ViewHolder> {

    private final List<Ingredient> mValues;


    public IngredientListRecyclerViewAdapter(List<Ingredient> mValues) {
        this.mValues = mValues;
    }

    @NonNull
    @Override
    public IngredientListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_list_content, parent, false);
        return new IngredientListRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mIngredientLabelView.setText(mValues.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIngredientLabelView;

        ViewHolder(View view) {
            super(view);
            mIngredientLabelView = (TextView) view.findViewById(R.id.ingredient_label);
        }
    }
}
