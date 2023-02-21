package com.codepath.bestsellerlistapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.bestsellerlistapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class BestSellerBooksFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_best_seller_books_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val recyclerView1 = view.findViewById<View>(R.id.list1) as RecyclerView
        val context = view.context
//        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        updateAdapter(progressBar, recyclerView)
        updateAdapter1(progressBar, recyclerView1)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
//                "https://api.themoviedb.org/3/trending/all/day", params, object : JsonHttpResponseHandler()
                  "https://api.themoviedb.org/3/movie/popular", params, object : JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                        // The wait for a response is over
                        progressBar.hide()

                        //TODO - Parse JSON into Models
                        val resultsJSON = json.jsonObject.get("results").toString()
                        Log.d("Trending", resultsJSON)

                        val gson = Gson()
                        val arrayBookType = object : TypeToken<List<BestSellerBook>>() {}.type
                        val models : List<BestSellerBook> = gson.fromJson(resultsJSON, arrayBookType) // Fix me!
                        recyclerView.adapter = BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("BestSellerBooksFragment", errorResponse)
                        }
                    }
                }]

    }

    private fun updateAdapter1(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
                "https://api.themoviedb.org/3/tv/popular", params, object : JsonHttpResponseHandler()
//                "https://api.themoviedb.org/3/trending/all/day", params, object : JsonHttpResponseHandler()
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                        // The wait for a response is over
                        progressBar.hide()

                        //TODO - Parse JSON into Models
                        val resultsJSON = json.jsonObject.get("results").toString()
                        Log.d("Trending", resultsJSON)

                        val gson = Gson()
                        val arrayBookType = object : TypeToken<List<BestSellerBook>>() {}.type
                        val models : List<BestSellerBook> = gson.fromJson(resultsJSON, arrayBookType) // Fix me!
                        recyclerView.adapter = BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("BestSellerBooksFragment", errorResponse)
                        }
                    }
                }]

    }
    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: BestSellerBook) {
        Toast.makeText(context, "test: " + item.name, Toast.LENGTH_LONG).show()

//        val intent = Intent(context, DetailActivity::class.java)
//        intent.putExtra("item_position", "1");
        // Launch the new activity
//        startActivity(intent);
//        intent.putExtra(ARTICLE_EXTRA, article)
//        context.startActivity(intent)
    }

}
