package com.android.moviecatalogueapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.moviecatalogueapp.data.source.remote.response.MoviesResultsItem
import com.android.moviecatalogueapp.databinding.ItemsMovieBinding
import com.android.moviecatalogueapp.utils.GlideLoadImage.load

class MovieListAdapter :
    ListAdapter<MoviesResultsItem, MovieListAdapter.MovieListViewHolder>(MovieComparator()) {

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallBack
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: MoviesResultsItem)
    }

    inner class MovieListViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesResultsItem) {
            binding.apply {
                imgItemPoster.load(movie.getPosterMovieImage())
                tvItemTitle.text = movie.title
                tvItemRating.text = movie.voteAverage.toString()
                tvItemOverview.text = movie.overview
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }
            }
        }
    }

    class MovieComparator : DiffUtil.ItemCallback<MoviesResultsItem>() {
        override fun areItemsTheSame(
            oldItem: MoviesResultsItem,
            newItem: MoviesResultsItem,
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MoviesResultsItem,
            newItem: MoviesResultsItem,
        ): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}