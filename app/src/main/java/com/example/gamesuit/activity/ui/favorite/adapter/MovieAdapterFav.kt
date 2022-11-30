package com.example.gamesuit.activity.ui.favorite.adapter


import android.content.Context
import android.graphics.Picture
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesuit.R
import com.example.gamesuit.activity.ui.favorite.model.Favorite
import com.squareup.picasso.Picasso


class MovieAdapterFav(context: Context) : RecyclerView.Adapter<MovieAdapterFav.MovieViewHolder>() {
    private val favmovie: MutableList<Favorite>
    private val context: Context

    init {
        this.context = context
        favmovie = ArrayList()
    }

    private fun add(item: Favorite) {
        favmovie.add(item)
        notifyItemInserted(favmovie.size - 1)
        notifyDataSetChanged()
    }

    fun addAll(favmovie: List<Favorite>) {
        for (favorite in favmovie) {
            add(favorite)
        }
        notifyDataSetChanged()
    }

    fun clear() {
        while (itemCount > 0) {
            remove(item)
        }
    }

    private fun remove(item: Favorite) {
        val position = favmovie.indexOf(item)
        if (position > -1) {
            favmovie.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    private val item: Favorite
        private get() = favmovie[0]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_fav, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val favorite = favmovie[position]
        holder.bind(favorite)
    }

    override fun getItemCount(): Int {
        return favmovie.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterImage: ImageView
        private val posterTitle: TextView
        private val posterReleaseDate: TextView
        private val posterDescription: TextView
        private val posterRating: RatingBar
        var viewForeground: RelativeLayout

        init {
            posterImage = itemView.findViewById(R.id.posterImage)
            posterTitle = itemView.findViewById(R.id.posterTitle)
            posterRating = itemView.findViewById(R.id.rating)
            posterDescription = itemView.findViewById(R.id.posterDescription)
            posterReleaseDate = itemView.findViewById(R.id.posterReleaseDate)
            viewForeground = itemView.findViewById(R.id.view_foreground)
        }

        fun bind(favorite: Favorite) {
            Picasso.get()
                .load(IMG_URL + favorite.posterPath)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(posterImage)
            posterTitle.text = favorite.title
            posterRating.rating = (favorite.voteAverage / 2).toFloat()
            posterDescription.text = favorite.overview
            posterReleaseDate.text = String.format(
                "%s %s",
                context.getString(R.string.detail_release_date), favorite.releaseDate
            )
            viewForeground.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(context, posterTitle.text, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    companion object {
        private const val IMG_URL = "http://image.tmdb.org/t/p/w185"
    }
}