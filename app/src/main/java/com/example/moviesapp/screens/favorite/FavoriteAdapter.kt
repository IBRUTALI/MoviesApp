package com.example.moviesapp.screens.favorite

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var listMovies = emptyList<MovieItem>()
    private var valueBoolean by Delegates.notNull<Boolean>()

    class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        valueBoolean = SharedPreferencesFavorite.getFavorite(MAIN, listMovies[position].id)
        holder.itemView.apply {
            title.text = listMovies[position].title
            date.text = listMovies[position].year
            imdb.text = listMovies[position].imDbRating

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
                .load(listMovies[position].image)
                .centerCrop()
                .placeholder(null)
                .listener(requestListener)
                .into(img_item)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieItem>) {
        listMovies = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: FavoriteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            FavoriteFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: FavoriteViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}