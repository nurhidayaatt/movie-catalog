package com.nurhidayaatt.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nurhidayaatt.core.R
import com.nurhidayaatt.core.databinding.ItemListBinding
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.utils.Constants.POSTER_BASE_URL

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class MovieViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) = with(binding) {
            Glide.with(itemView).load(
                String.format(
                    root.resources.getString(R.string.format_url),
                    POSTER_BASE_URL,
                    movie.posterPath
                )
            ).placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error).transition(
                DrawableTransitionOptions.withCrossFade()
            ).into(ivPoster)
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvOverview.text = movie.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.apply {
            bind(movie)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<Movie>) {
        differ.submitList(list)
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }
}
