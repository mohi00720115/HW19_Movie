package com.example.hw19_movie.ui.coming_soon

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
import com.example.hw19_movie.databinding.FragmentCommingSoonMovieBinding
import com.example.hw19_movie.ui.MovieAdapter
import com.example.hw19_movie.ui.movie.MovieFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComingSoonMovieFragment : Fragment(R.layout.fragment_comming_soon_movie) {
    private lateinit var binding: FragmentCommingSoonMovieBinding
    private val viewModel: ComingSoonViewModel by viewModels()
    private lateinit var adapterComingSoon: MovieAdapter
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

    private fun observeLiveData() {
        viewModel.getAllMovieService(35)
        viewModel.comingSoonMovieList.observe(viewLifecycleOwner) {
            adapterComingSoon.submitList(it)
        }
    }

    /**
     * Create and set Adapter
     */
    private fun setAdapter() {
        adapterComingSoon = MovieAdapter({
            navController.navigate(MovieFragmentDirections.actionGlobalDialogMovieFragment())
        }) {
            Log.w(ContentValues.TAG, "setAdapter: ")
        }
        binding.recyclerViewComingSoon.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewComingSoon.adapter = adapterComingSoon
    }

}