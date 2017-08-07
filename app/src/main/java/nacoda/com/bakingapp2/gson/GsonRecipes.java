package nacoda.com.bakingapp2.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mayburger on 8/7/2017.
 */

public class GsonRecipes {

    @SerializedName("results")
    public List<Results> results;


    public class Results {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("servings")
        public String servings;
        @SerializedName("ingredients")
        public List<Ingredients> ingredients;
        @SerializedName("steps")
        public List<Steps> steps;

        public class Ingredients {
            @SerializedName("quantity")
            public String quantity;
            @SerializedName("measure")
            public String measure;
            @SerializedName("ingredient")
            public String ingredient;
        }

        public class Steps {
            @SerializedName("id")
            public String id;
            @SerializedName("shortDescription")
            public String shortDescription;
            @SerializedName("description")
            public String description;
            @SerializedName("videoURL")
            public String videoURL;
        }
    }
}
