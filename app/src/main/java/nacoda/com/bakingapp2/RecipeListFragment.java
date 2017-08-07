package nacoda.com.bakingapp2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import nacoda.com.bakingapp2.adapters.RecipeListAdapter;
import nacoda.com.bakingapp2.gson.GsonRecipes;
import nacoda.com.bakingapp2.utils.NetworkUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {

    GsonRecipes gsonRecipes;
    RequestQueue requestQueue;
    StringRequest recipeRequest;
    RecyclerView rvRecipesList;
    SwipeRefreshLayout swipeRefreshRecipe;
    TextView tvError;
    ImageView ivError;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        rvRecipesList = (RecyclerView) v.findViewById(R.id.rvRecipesList);
        swipeRefreshRecipe = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshRecipes);
        tvError = (TextView) v.findViewById(R.id.tvError);
        ivError = (ImageView) v.findViewById(R.id.ivError);

        requestQueue = Volley.newRequestQueue(getActivity());

        swipeRefreshRecipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        getData();

        return v;
    }

    private void getData() {
        rvRecipesList.setVisibility(View.GONE);
        swipeRefreshRecipe.setRefreshing(true);

        recipeRequest = new StringRequest(Request.Method.GET, NetworkUtils.recipeUrl().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String data) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                gsonRecipes = gson.fromJson("{\"results\": " + data + "}", GsonRecipes.class);

                onSuccess();

                RecipeListAdapter adapter = new RecipeListAdapter(getActivity(), gsonRecipes.results);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                rvRecipesList.setLayoutManager(linearLayoutManager);
                rvRecipesList.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError();
            }
        });

        requestQueue.add(recipeRequest);
    }

    private void onError() {
        ivError.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.VISIBLE);
        swipeRefreshRecipe.setRefreshing(false);
    }

    private void onSuccess() {
        rvRecipesList.setVisibility(View.VISIBLE);
        ivError.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
        swipeRefreshRecipe.setRefreshing(false);
    }


}
