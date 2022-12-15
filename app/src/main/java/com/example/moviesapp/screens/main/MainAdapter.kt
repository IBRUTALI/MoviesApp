package com.example.moviesapp.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.models.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var moviesList = emptyList<MovieItem>()

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.apply {
            title.text = moviesList[position].title
            date.text = moviesList[position].year
            imdb.text = if (moviesList[position].imDbRating.isNotEmpty()) {
                moviesList[position].imDbRating
            } else {
                "0.0"
            }

            Glide.with(MAIN)
                .load(moviesList[position].image)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_block_24)
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