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
import com.nurhidayaatt.core.domain.model.TvShow
import com.nurhidayaatt.core.utils.Constants.POSTER_BASE_URL

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<TvShow>() {

        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class TvShowViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) = with(binding) {
            Glide.with(itemView).load(
                String.format(
                    root.resources.getString(R.string.format_url),
                    POSTER_BASE_URL,
                    tvShow.posterPath
                )
            ).placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error).transition(
                DrawableTransitionOptions.withCrossFade()
            ).into(ivPoster)
            tvTitle.text = tvShow.name
            tvReleaseDate.text = tvShow.firstAirDate
            tvOverview.text = tvShow.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = differ.currentList[position]
        holder.apply {
            bind(tvShow)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(tvShow)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<TvShow>) {
        differ.submitList(list)
    }

    private var onItemClickListener: ((TvShow) -> Unit)? = null

    fun setOnItemClickListener(listener: (TvShow) -> Unit) {
        onItemClickListener = listener
    }
}
