package com.udacity.sandwichclub.utils

import com.udacity.sandwichclub.model.Sandwich
import org.json.JSONArray
import org.json.JSONObject

object JsonUtils {

    fun parseSandwichJson(json: String): Sandwich? {
        if (json.isBlank()) {
            return null
        }

        val sandwichJsonObject = JSONObject(json)
        val nameJsonObject = sandwichJsonObject.getJSONObject("name")

        val mainName = nameJsonObject.getString("mainName")
        val alsoKnownAs = getArrayValues(nameJsonObject.getJSONArray("alsoKnownAs"))
        val placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin")
        val description =  sandwichJsonObject.getString("description")
        val image = sandwichJsonObject.getString("image")
        val ingredients = getArrayValues(sandwichJsonObject.getJSONArray("ingredients"))

        return Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients)
    }

    private fun getArrayValues(jsonArray: JSONArray?): MutableList<String>? {
        val values = mutableListOf<String>()

        if (jsonArray != null && jsonArray.length() > 0) {
            for (it in 0..(jsonArray.length() - 1)) {
                values.add(jsonArray[it].toString())
            }
        }
        return values
    }
}
