package com.example.hw19_movie.ui.my_favorite

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.hw19_movie.R
import com.example.hw19_movie.databinding.FragmentMyFavoriteMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavoriteMovieFragment : Fragment(R.layout.fragment_my_favorite_movie) {
    private lateinit var binding: FragmentMyFavoriteMovieBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = this
    }
}