package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        if (json == null || json.isEmpty()) {
            return null;
        }

        JSONObject sandwichJsonObject = new JSONObject(json);
        JSONObject nameJsonObject = sandwichJsonObject.getJSONObject("name");

        String mainName = nameJsonObject.getString("mainName");
        List<String> alsoKnownAs = getArrayValues(nameJsonObject.getJSONArray("alsoKnownAs"));
        String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
        String description =  sandwichJsonObject.getString("description");
        String image = sandwichJsonObject.getString("image");
        List<String> ingredients = getArrayValues(sandwichJsonObject.getJSONArray("ingredients"));

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static List<String> getArrayValues(JSONArray jsonArray) throws JSONException {
        List<String> values = new ArrayList<>();

        if (jsonArray != null && jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                values.add(jsonArray.getString(i));
            }
        }
        return values;
    }
}
