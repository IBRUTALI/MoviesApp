package com.example.moviesapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentFavoriteBinding
import com.example.moviesapp.databinding.FragmentMainBinding
import com.example.moviesapp.models.MovieItem
import com.example.moviesapp.screens.main.MainAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private var mBinding: FragmentFavoriteBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { FavoriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
        binding.apply {
            rvFavorite.adapter = adapter
            rvFavorite.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        viewModel.getAllMovies().observe(this, Observer {list->
            adapter.setList(list.asReversed())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        fun clickMovie(model: MovieItem) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }
}