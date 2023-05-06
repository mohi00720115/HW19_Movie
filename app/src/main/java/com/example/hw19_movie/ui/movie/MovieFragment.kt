package com.example.hw19_movie.ui.movie

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.FragmentMovieBinding
import com.example.hw19_movie.ui.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var navController: NavController
    private val viewModel: MovieViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner

        navController = findNavController()
        adapterMovie = MovieAdapter {
            viewModel.insert(it)
        }
        setAdapter()

        viewModel.getAllMovieService(20)
        viewModel.movieList.observe(viewLifecycleOwner) {
            adapterMovie.submitList(it)
        }

    }

    /**
     * Create and set Adapter
     */
    private fun setAdapter() {
        binding.recyclerViewMovie.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewMovie.adapter = adapterMovie
    }


}