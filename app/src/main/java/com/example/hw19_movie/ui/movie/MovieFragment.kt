package com.example.hw19_movie.ui.movie

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        adapterMovie = MovieAdapter({
            navController.navigate(MovieFragmentDirections.actionGlobalDialogMovieFragment())
        }) {
            viewModel.insert(it)
        }

        setAdapter()

        viewModel.getAllMovieService(1)
        viewModel.movieList.observe(viewLifecycleOwner) {
            adapterMovie.submitList(it)
            adapterMovie.notifyDataSetChanged()
            Log.e(TAG, "onViewCreated: $it")
        }

        binding.recyclerViewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapterMovie.itemCount - 1) {
                    viewModel.nextPage()
                }
            }
        })

    }

    /**
     * Create and set Adapter
     */
    private fun setAdapter() {
        binding.recyclerViewMovie.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewMovie.adapter = adapterMovie
    }





}