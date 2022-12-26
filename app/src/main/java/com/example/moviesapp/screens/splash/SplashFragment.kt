package com.example.moviesapp.screens.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.moviesapp.MAIN
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: Fragment() {
    private var mBinding: FragmentSplashBinding ?= null
    private val binding get() = mBinding!!

    private fun init(view: View) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.splashProgress.max = 20
            val value = 19
            ObjectAnimator.ofInt(binding.splashProgress, "progress", value).setDuration(2000).start()
            delay(2000)
            view.findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        activity?.actionBar?.hide()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}