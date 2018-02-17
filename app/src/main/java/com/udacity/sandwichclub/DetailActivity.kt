package com.udacity.sandwichclub

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast

import com.squareup.picasso.Picasso
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.utils.JsonUtils

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ingredientsIv = findViewById<ImageView>(R.id.image_iv)

        val intent = intent
        if (intent == null) {
            closeOnError()
        }

        val position = intent!!.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION)
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError()
            return
        }

        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        val json = sandwiches[position]
        val sandwich = JsonUtils.parseSandwichJson(json)
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError()
            return
        }

        populateUI()
        Picasso.with(this)
                .load(sandwich.image)
                .into(ingredientsIv)

        title = sandwich.mainName
    }

    private fun closeOnError() {
        finish()
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show()
    }

    private fun populateUI() {

    }

    companion object {

        val EXTRA_POSITION = "extra_position"
        private val DEFAULT_POSITION = -1
    }
}
