package com.example.hw19_movie.ui.coming_soon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.FragmentCommingSoonMovieBinding
import com.example.hw19_movie.databinding.FragmentPopularMoviesBinding
import com.example.hw19_movie.ui.MovieAdapter
import com.example.hw19_movie.ui.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComingSoonMovieFragment : Fragment(R.layout.fragment_comming_soon_movie) {
    private lateinit var binding : FragmentCommingSoonMovieBinding
    private val viewModel: ComingSoonViewModel by viewModels()
    private lateinit var adapterComingSoon: MovieAdapter
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setAdapter()

    }

    /**
     * Create and set Adapter
     */
    private fun setAdapter() {
        adapterComingSoon = MovieAdapter{

        }
        binding.recyclerViewComingSoon.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewComingSoon.adapter = adapterComingSoon
    }

}