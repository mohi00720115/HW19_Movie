package com.example.hw19_movie.ui

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw19_movie.R
import com.example.hw19_movie.model.ui.MovieItem
import com.example.hw19_movie.databinding.ItemMovies2Binding

class MovieAdapter(
    private val clickOnItemMovies: (id: Int) -> Unit,
    private val clickOnLikeIcons: (item: MovieItem) -> Unit
) : ListAdapter<MovieItem, MovieAdapter.ViewHolder>(object : DiffUtil.ItemCallback<MovieItem?>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }
}) {
    inner class ViewHolder(private val binding: ItemMovies2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        private fun likeOrOnLike(item: MovieItem) {
            if (!item.flag) {
                item.flag = true
                binding.ivLike2.setImageResource(R.drawable.baseline_favorite)
            } else {
                item.flag = false
                binding.ivLike2.setImageResource(R.drawable.favorite_border)
            }
        }

        fun bind(item: MovieItem) {
            Log.e(TAG, "Movie Adapter: page ${item.id}")
            Log.e(TAG, "Movie Adapter: results ${item.posterPath}")
            Log.e(TAG, "Movie Adapter: total_pages ${item.releaseDate}")
            Log.e(TAG, "Movie Adapter: total_results ${item.title}")

            binding.tvName2.text = item.title.toString()
            binding.tvTitle2.text = item.id.toString()
            binding.tvRealizeDate2.text = item.releaseDate.toString()

            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500" + item.posterPath)
//                .placeholder(R.drawable.loading_animation)
                .into(binding.imageViewMovie2)

            binding.constraintLayout2.setOnClickListener {
                clickOnItemMovies(item.id!!)
                Log.e(TAG, "bind: ${item.id}")
            }

            binding.ivLike2.setOnClickListener {
                likeOrOnLike(item)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovies2Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

}