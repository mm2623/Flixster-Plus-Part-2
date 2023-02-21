package com.codepath.bestsellerlistapp

import android.graphics.Movie
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var launchTextView: TextView
    private lateinit var ratingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        descriptionTextView = findViewById(R.id.mediaByline)
        launchTextView = findViewById(R.id.launch)
        ratingTextView = findViewById(R.id.rating)
        // TODO: Get the extra from the Intent
//        val movie = intent.getSerializableExtra(ARTICLE_EXTRA) as BestSellerBook
        val movieName = intent.getStringExtra("name")
        val movieTitle = intent.getStringExtra("title")
        val movieDescription = intent.getStringExtra("description")
        val movieImgUrl = intent.getStringExtra("imageUrl")
        val movieBackdropImgUrl = intent.getStringExtra("backdropImageUrl")
        val movieLaunchDate = intent.getStringExtra("launchDate")
        val movieReleaseDate = intent.getStringExtra("releaseDate")
        val movieRating = intent.getStringExtra("rating")
        // TODO: Set the title, byline, and abstract information from the article
        if (movieTitle == null){
            titleTextView.text = movieName
        }
        else {
            titleTextView.text = movieTitle
        }
        descriptionTextView.text = movieDescription
        if (movieLaunchDate == null){
            launchTextView.text = "Release Date :- $movieReleaseDate"
        }
        else {
            launchTextView.text = "Release Date :- $movieLaunchDate"
        }

        ratingTextView.text = "Rating :- $movieRating %"
        // TODO: Load the media image

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/$movieBackdropImgUrl")
            .centerCrop() // scale image to fill the entire ImageView
            .transform(RoundedCorners(30))
            .placeholder(R.drawable.ic_launcher_background)
            .into(mediaImageView)
    }
}