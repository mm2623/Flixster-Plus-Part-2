package com.codepath.bestsellerlistapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codepath.bestsellerlistapp.R.id


/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */

const val ARTICLE_EXTRA = "ARTICLE_EXTRA"

class BestSellerBooksRecyclerViewAdapter(
    private val movies: List<BestSellerBook>,
    private val mListener: OnListFragmentInteractionListener?
    )
    : RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder>()
    {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestSellerBook? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
//        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById<ImageView>(id.movie_image) as ImageView

        override fun toString(): String {
//            return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
            return mMovieTitle.toString()
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        if (movie.title == null){
            holder.mMovieTitle.text = movie.name
        }
        else {
            holder.mMovieTitle.text = movie.title
        }

//        holder.mMovieDescription.text = movie.description

        val radius = 30; // corner radius, higher value = more rounded
        val margin = 10; // crop margin, set to 0 for corners with no crop
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.imageUrl)
            .centerCrop() // scale image to fill the entire ImageView
            .transform(RoundedCorners(30))
            .placeholder(R.drawable.ic_launcher_background)
//            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("name", movie.name)
            intent.putExtra("title", movie.title)
            intent.putExtra("description", movie.description)
            intent.putExtra("imageUrl", movie.imageUrl)
            intent.putExtra("backdropImageUrl", movie.backdropImageUrl)
            intent.putExtra("launchDate", movie.launchDate)
            intent.putExtra("releaseDate", movie.releaseDate)
            intent.putExtra("rating", movie.rating)
            holder.itemView.context.startActivity(intent)

//            holder.mItem?.let { book ->
//                mListener?.onItemClick(book)
//            }
        }
    }


//    override fun onClick(v: View?) {
//        // TODO: Get selected article
//        val movie = movies[absoluteAdapterPosition]
//        // TODO: Navigate to Details screen and pass selected article
//        val intent = Intent(mListener, DetailActivity::class.java)
//        val intent = Intent(this@BestSellerBooksRecyclerViewAdapter, DetailActivity::class.java)
//        intent.putExtra(ARTICLE_EXTRA, movie)
//        mListener.startActivity(intent)
//    }
    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}