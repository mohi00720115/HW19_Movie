package com.example.hw19_movie.ui.popular_movie

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.FragmentPopularMoviesBinding
import com.example.hw19_movie.ui.MovieAdapter
import com.example.hw19_movie.ui.movie.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment(R.layout.fragment_popular_movies) {
    private lateinit var binding: FragmentPopularMoviesBinding
    private val viewModel: PopularMovieViewModel by viewModels()
    private lateinit var adapterMyFavMovie: MovieAdapter
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
        setUpUi()

    }

    private fun setUpUi() {
        observeLiveData()
        setAdapter()
    }

    /**
     * Observe and submitList adapter
     */
    private fun observeLiveData() {
        viewModel.getAllMovieService(20)
        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            adapterMyFavMovie.submitList(it)
        }
    }

    /**
     * Create and set Adapter
     */
    private fun setAdapter() {
        adapterMyFavMovie = MovieAdapter({
            navController.navigate(MovieFragmentDirections.actionGlobalDialogMovieFragment())
        }) {
            Log.w(ContentValues.TAG, "setAdapter: ")
        }
        binding.recyclerViewPopular.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewPopular.adapter = adapterMyFavMovie
    }

}
