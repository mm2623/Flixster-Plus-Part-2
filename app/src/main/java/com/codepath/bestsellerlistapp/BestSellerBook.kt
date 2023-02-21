package com.codepath.bestsellerlistapp

import com.google.gson.annotations.SerializedName

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The Model for storing a single book from the NY Times API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class BestSellerBook {

    @JvmField

    @SerializedName("title")
    var title: String? = null

    @SerializedName("name")
    var name: String? = null

    //TODO description
    @SerializedName("overview")
    var description: String? = null

    //TODO Poster
    @SerializedName("poster_path") //poster_path
    var imageUrl: String? = null

    //TODO Backdrop
    @SerializedName("backdrop_path") //backdrop_path
    var backdropImageUrl: String? = null

    //TODO LaunchDate
    @SerializedName("first_air_date")
    var launchDate: String? = null

    //TODO LaunchDate
    @SerializedName("release_date")
    var releaseDate: String? = null

    //TODO Rating
    @SerializedName("vote_average")
    var rating: String? = null

}