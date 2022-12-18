package com.example.moviesapp.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.SaveShared
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.databinding.FragmentFavoriteBinding
import com.example.moviesapp.models.MovieItem
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_movie.*

class DetailFragment : Fragment() {
    private var mBinding: FragmentDetailBinding? = null
    private val binding get() = mBinding!!
    private lateinit var currentMovie: MovieItem
    private var isFavorite = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val valueBoolean = SaveShared.getFavorite(MAIN, currentMovie.id)
        val viewModel = ViewModelProvider(this)[DetailFragmentViewModel::class.java]

        if(isFavorite != valueBoolean) {
            binding.favoriteDetail.setImageResource(R.drawable.ic_baseline_star_rate_24)
        } else {
            binding.favoriteDetail.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }

        viewModel.getMovieTrailer(currentMovie.id)
        viewModel.movieTrailer.observe(viewLifecycleOwner, Observer { movie ->
            binding.descriptionDetail.text = movie.body()?.videoDescription ?: "No description"
        })
        binding.apply {
            Glide.with(MAIN)
                .load(currentMovie.image)
                .placeholder(R.drawable.ic_baseline_block_24)
                .into(imgDetail)
            val title = currentMovie.title + "(${currentMovie.year})"
            titleDetail.text = title
            imdbDetail.text = if (currentMovie.imDbRating.isNotEmpty()) {
                currentMovie.imDbRating
            } else {
                "0.0"
            }
            favoriteDetail.setOnClickListener {
                isFavorite = if (isFavorite == valueBoolean) {
                    favoriteDetail.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    SaveShared.setFavorite(MAIN, currentMovie.id, true)
                    viewModel.insertMovie(currentMovie){}
                    true
                } else {
                    favoriteDetail.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    SaveShared.setFavorite(MAIN, currentMovie.id, false)
                    viewModel.deleteMovie(currentMovie){}
                    false
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as MovieItem
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}