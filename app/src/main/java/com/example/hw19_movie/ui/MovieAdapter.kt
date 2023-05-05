package com.example.hw19_movie.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw19_movie.data.MovieItem
import com.example.hw19_movie.databinding.ItemMoviesBinding
import javax.inject.Inject

class MovieAdapter @Inject constructor() :
    ListAdapter<MovieItem, MovieAdapter.ViewHolder>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItem) {
            Log.e(TAG, "Movie Adapter: page ${item.id}")
            Log.e(TAG, "Movie Adapter: results ${item.posterPath}")
            Log.e(TAG, "Movie Adapter: total_pages ${item.releaseDate}")
            Log.e(TAG, "Movie Adapter: total_results ${item.title}")

            binding.tvName.text = item.title.toString()
            binding.tvTitle.text = item.id.toString()
            binding.tvDescription.text = item.releaseDate.toString()

            Glide.with(binding.root)
                .load(item.posterPath)
//                .placeholder(R.drawable.loading_animation)
                .into(binding.imageViewMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

}