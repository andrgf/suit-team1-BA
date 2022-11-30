package com.example.gamesuit.activity.ui.favorite.fragment


import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gamesuit.R
import com.example.gamesuit.activity.ui.favorite.adapter.MovieAdapterFav
import com.example.gamesuit.activity.ui.favorite.model.Favorite
import java.util.*


class FragmentMovie : Fragment() {
    private var rootView: View? = null
    private var movieAdapter: MovieAdapterFav? = null
    private var emptystate: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_fragment_movie, container, false)
        initView()
        return rootView
    }

    @SuppressLint("Recycle", "Range", "UseRequireInsteadOfGet")
    private fun loadData() {
        val cursor = Objects.requireNonNull(context)!!.contentResolver.query(
            CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (cursor != null) {
            movieAdapter!!.clear()
            val favoriteList: ArrayList<Favorite> = ArrayList()
            var image: String?
            var title: String?
            var description: String?
            var releasedata: String?
            var voteAverage: Double
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    image = cursor.getString(cursor.getColumnIndex("image"))
                    title = cursor.getString(cursor.getColumnIndex("title"))
                    releasedata = cursor.getString(cursor.getColumnIndex("releasedate"))
                    description = cursor.getString(cursor.getColumnIndex("description"))
                    voteAverage = cursor.getDouble(cursor.getColumnIndex("vote"))
                    val favoriteTv = Favorite(image, title, releasedata, description, voteAverage)
                    favoriteList.add(favoriteTv)
                    cursor.moveToNext()
                }
            }
            if (favoriteList.size === 0) {
                emptystate!!.visibility = View.VISIBLE
            } else {
                movieAdapter!!.addAll(favoriteList)
                emptystate!!.visibility = View.GONE
            }
        } else {
            emptystate!!.visibility = View.VISIBLE
        }
    }

    private fun initView() {
        val refreshLayout = rootView!!.findViewById<SwipeRefreshLayout>(R.id.refresh)
        emptystate = rootView!!.findViewById(R.id.emptystate)
        refreshLayout.isEnabled = false
        refreshLayout.isRefreshing = false
        val dataMovie = rootView!!.findViewById<RecyclerView>(R.id.dataMovieFav)
        movieAdapter = MovieAdapterFav(requireContext())
        @SuppressLint("WrongConstant") val gridLayoutManager = GridLayoutManager(
            context, 1, GridLayoutManager.VERTICAL, false
        )
        dataMovie.layoutManager = gridLayoutManager
        dataMovie.setHasFixedSize(true)
        dataMovie.adapter = movieAdapter
        loadData()
    }

    companion object {
        private const val PROVIDER_NAME = "com.miftahjuanda.movies"
        private const val URL = "content://" + PROVIDER_NAME
        private val CONTENT_URI = Uri.parse(URL)
    }
}