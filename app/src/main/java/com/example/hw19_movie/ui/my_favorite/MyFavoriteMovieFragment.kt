package com.example.hw19_movie.ui.my_favorite

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
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.FragmentMyFavoriteMovieBinding
import com.example.hw19_movie.ui.MovieAdapter
import com.example.hw19_movie.ui.movie.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavoriteMovieFragment : Fragment(R.layout.fragment_my_favorite_movie) {
    private lateinit var binding: FragmentMyFavoriteMovieBinding
    private val viewModel: MyFavoriteViewModel by viewModels()
    private lateinit var adapterMyFavMovie: MovieAdapter
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
        adapterMyFavMovie = MovieAdapter({
            navController.navigate(MovieFragmentDirections.actionGlobalDialogMovieFragment())
        }) {
            Log.w(TAG, "setAdapter: ")
        }
        binding.recyclerViewMyFavMovie.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewMyFavMovie.adapter = adapterMyFavMovie
    }

}