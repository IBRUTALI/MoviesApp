package com.example.moviesapp.screens.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.Singletons
import com.example.moviesapp.data.sharedprefs.settings.AppSettings
import com.example.moviesapp.databinding.FragmentMainBinding
import com.example.moviesapp.models.MovieItem
import com.example.moviesapp.screens.settings.SettingsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!
    private val adapter by lazy { MainAdapter() }
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        menuProvider()
    }

    private fun init() {
        binding.apply {
            rvMain.adapter = adapter
            rvMain.layoutManager = GridLayoutManager(requireContext(), Singletons.getGrid())
        }
        viewModel.getPopularMovies()
        viewModel.initDatabase()

        viewModel.myMovie.observe(viewLifecycleOwner, { list ->
            adapter.setList(list.body()!!.items)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    private fun menuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.item_favorite -> {
                        MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
                        true
                    }
                    R.id.item_settings -> {
                        MAIN.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                        true
                    }else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        fun clickMovie(model: MovieItem) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }
}