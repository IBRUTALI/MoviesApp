package com.example.moviesapp.screens.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.data.sharedprefs.favorite.SharedPreferencesFavorite
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.models.MovieItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_movie.view.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var mBinding: FragmentDetailBinding? = null
    private val binding get() = mBinding!!
    private lateinit var currentMovie: MovieItem
    private var isFavorite = false
    private val viewModel: DetailFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val valueBoolean = viewModel.getFavorite(currentMovie.id)

        if(isFavorite != valueBoolean) {
            binding.favoriteDetail.setImageResource(R.drawable.ic_baseline_star_rate_24)
        } else {
            binding.favoriteDetail.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }

        viewModel.getMovieTrailer(currentMovie.id)
        viewModel.movieTrailer.observe(viewLifecycleOwner, { movie ->
            binding.descriptionDetail.text = movie.body()?.videoDescription ?: "No description"
        })

        val requestListener = object: RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.detailProgress.visibility = View.VISIBLE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.detailProgress.visibility = View.GONE
                return false
            }

        }

        binding.apply {
            Glide.with(MAIN)
                .load(currentMovie.image)
                .listener(requestListener)
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
                    viewModel.setFavorite(currentMovie.id, true)
                    viewModel.insertMovie(currentMovie){}
                    true
                } else {
                    favoriteDetail.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    viewModel.setFavorite(currentMovie.id, false)
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