package com.example.hw19_movie.ui.coming_soon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.FragmentCommingSoonMovieBinding
import com.example.hw19_movie.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComingSoonMovieFragment : Fragment(R.layout.fragment_comming_soon_movie) {
    private lateinit var binding : FragmentCommingSoonMovieBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = this
    }
}