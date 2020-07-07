package com.mbelisaire.familysecret;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mbelisaire.familysecret.dao.Recipe;
import com.mbelisaire.familysecret.dao.RecipesFactory;
import com.mbelisaire.familysecret.helpers.GridLayoutHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recipesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recipesRecycler = findViewById(R.id.recipes_list);

        if (RecipesFactory.ITEMS.isEmpty()) {
            requestRecipesData();
        } else {
            setupRecyclerView(recipesRecycler);
        }
    }

    JsonArrayRequest getRecipesJsonArrayRequest = new JsonArrayRequest(Request.Method.GET, AppConstants.GET_RECIPES_URL, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            String recipesJSON = response.toString();
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Recipe>>(){}.getType();
            ArrayList<Recipe> recipes = gson.fromJson(recipesJSON, listType);
            RecipesFactory.addItems(recipes);
            setupRecyclerView(recipesRecycler);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley", "Failed to fetch recipes data. " + error.getMessage());
        }
    });



    private void requestRecipesData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getRecipesJsonArrayRequest);
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RecipesListRecyclerViewAdapter(this, RecipesFactory.ITEMS));
        int spanCount = GridLayoutHelper.calculateNumberOfColumns(this, AppConstants.RECIPE_CARD_WIDTH);
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
    }
}