package com.android.moviecatalogueapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.moviecatalogueapp.data.source.remote.response.TvShowsResultsItem
import com.android.moviecatalogueapp.databinding.ItemsMovieBinding
import com.android.moviecatalogueapp.utils.GlideLoadImage.load

class TvShowsListAdapter :
    ListAdapter<TvShowsResultsItem, TvShowsListAdapter.TvShowsListViewHolder>(TvShowsComparator()) {

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallBack
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: TvShowsResultsItem)
    }

    inner class TvShowsListViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: TvShowsResultsItem) {
            binding.apply {
                imgItemPoster.load(movie.getPosterMovieImage())
                tvItemTitle.text = movie.name
                tvItemRating.text = movie.voteAverage.toString()
                tvItemOverview.text = movie.overview
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }
            }
        }
    }

    class TvShowsComparator : DiffUtil.ItemCallback<TvShowsResultsItem>() {
        override fun areItemsTheSame(
            oldItemResultsItem: TvShowsResultsItem,
            newItemResultsItem: TvShowsResultsItem,
        ): Boolean =
            oldItemResultsItem.id == newItemResultsItem.id

        override fun areContentsTheSame(
            oldItemResultsItem: TvShowsResultsItem,
            newItemResultsItem: TvShowsResultsItem,
        ): Boolean =
            oldItemResultsItem == newItemResultsItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsListViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowsListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}