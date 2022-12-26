package com.example.moviesapp.screens.main

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.data.sharedprefs.favorite.SharedPreferencesFavorite
import com.example.moviesapp.models.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlin.properties.Delegates

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var moviesList = emptyList<MovieItem>()
    private var valueBoolean by Delegates.notNull<Boolean>()

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        valueBoolean = SharedPreferencesFavorite.getFavorite(MAIN, moviesList[position].id)
        holder.itemView.apply {
            title.text = moviesList[position].title
            date.text = moviesList[position].year
            imdb.text = if (moviesList[position].imDbRating.isNotEmpty()) {
                moviesList[position].imDbRating
            } else {
                "0.0"
            }

            if(valueBoolean) {
                img_favorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
            } else {
                img_favorite.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }

            val requestListener = object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    item_progress.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    item_progress.visibility = View.GONE
                    return false
                }

            }

            Glide.with(MAIN)
                .load(moviesList[position].image)
                .centerCrop()
                .placeholder(null)
                .listener(requestListener)
                .into(img_item)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieItem>) {
        moviesList = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.clickMovie(moviesList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}