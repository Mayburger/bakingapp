package nacoda.com.bakingapp2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nacoda.com.bakingapp2.R;
import nacoda.com.bakingapp2.gson.GsonRecipes;

/**
 * Created by Mayburger on 4/19/2017.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private Context context;
    private List<GsonRecipes.Results> recipeResults;

    public RecipeListAdapter(Context context, List<GsonRecipes.Results> recipeResults) {
        this.context = context;
        this.recipeResults = recipeResults;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recipes, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvCakeName.setText(recipeResults.get(position).name);
        holder.tvStepsCount.setText(recipeResults.get(position).steps.size() + " Steps");
        holder.tvServingsCount.setText(recipeResults.get(position).servings);


    }

    @Override
    public int getItemCount() {
        return recipeResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCakeName, tvStepsCount, tvServingsCount;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCakeName = (TextView) itemView.findViewById(R.id.tvCakeName);
            tvStepsCount = (TextView) itemView.findViewById(R.id.tvStepsCount);
            tvServingsCount = (TextView) itemView.findViewById(R.id.tvServingsCount);

        }
    }
}