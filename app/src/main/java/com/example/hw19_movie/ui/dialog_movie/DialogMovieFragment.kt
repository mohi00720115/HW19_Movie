package com.example.hw19_movie.ui.dialog_movie

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.DetailMoviesDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogMovieFragment : DialogFragment(R.layout.detail_movies_dialog) {
    private lateinit var binding: DetailMoviesDialogBinding
    private val viewModel: DialogMovieViewModel by viewModels()
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()


    }
}