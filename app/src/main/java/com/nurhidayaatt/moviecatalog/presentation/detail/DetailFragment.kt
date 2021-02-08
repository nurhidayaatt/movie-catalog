package com.nurhidayaatt.moviecatalog.presentation.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.nurhidayaatt.core.domain.model.Movie
import com.nurhidayaatt.core.domain.model.TvShow
import com.nurhidayaatt.core.utils.Constants.BACKDROP_BASE_URL
import com.nurhidayaatt.core.utils.Constants.POSTER_BASE_URL
import com.nurhidayaatt.moviecatalog.R
import com.nurhidayaatt.moviecatalog.databinding.DetailFragmentBinding
import com.nurhidayaatt.moviecatalog.utils.formatString
import com.nurhidayaatt.moviecatalog.utils.showSnackBar
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModelScope = getKoin().getOrCreateScope("Scope3", named("ViewModel"))
    private val viewModel: DetailViewModel by viewModelScope.viewModel(this)

    private val args: DetailFragmentArgs by navArgs()
    private var movie: Movie? = null
    private var tvShow: TvShow? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = args.movie
        tvShow = args.tvshow
        movie?.let {
            initView(it)
        }
        tvShow?.let {
            initView(it)
        }
    }

    private fun initView(movie: Movie) {
        with(binding) {
            progressBar.visibility = View.GONE
            Glide.with(this@DetailFragment).asBitmap().load(
                String.format(
                    resources.getString(R.string.format_url),
                    BACKDROP_BASE_URL,
                    movie.backdropPath
                )
            ).placeholder(
                R.drawable.ic_placeholder
            ).error(
                R.drawable.ic_error
            ).transition(
                BitmapTransitionOptions.withCrossFade()
            ).into(ivBanner)

            Glide.with(this@DetailFragment).asBitmap().load(
                String.format(
                    resources.getString(R.string.format_url),
                    POSTER_BASE_URL,
                    movie.posterPath
                )
            ).placeholder(
                R.drawable.ic_placeholder
            ).error(
                R.drawable.ic_error
            ).transition(
                BitmapTransitionOptions.withCrossFade()
            ).into(ivPoster)

            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvVoteAverage.text = movie.voteAverage.toString()
            tvVoteCount.text = movie.voteCount.toString().formatString()
            tvOverview.text = movie.overview

            var statusFavorite = movie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (statusFavorite) {
                    coordinator.showSnackBar("Added to Favorite")
                } else {
                    coordinator.showSnackBar("Removed from Favorite")
                }
            }
        }
    }

    private fun initView(tvShow: TvShow) {
        with(binding) {
            progressBar.visibility = View.GONE
            Glide.with(this@DetailFragment).asBitmap().load(
                String.format(
                    resources.getString(R.string.format_url),
                    BACKDROP_BASE_URL,
                    tvShow.backdropPath
                )
            ).placeholder(
                R.drawable.ic_placeholder
            ).error(
                R.drawable.ic_error
            ).transition(
                BitmapTransitionOptions.withCrossFade()
            ).into(ivBanner)

            Glide.with(this@DetailFragment).asBitmap().load(
                String.format(
                    resources.getString(R.string.format_url),
                    POSTER_BASE_URL,
                    tvShow.posterPath
                )
            ).placeholder(
                R.drawable.ic_placeholder
            ).error(
                R.drawable.ic_error
            ).transition(
                BitmapTransitionOptions.withCrossFade()
            ).into(ivPoster)

            tvTitle.text = tvShow.name
            tvReleaseDate.text = tvShow.firstAirDate
            tvVoteAverage.text = tvShow.voteAverage.toString()
            tvVoteCount.text = tvShow.voteCount.toString().formatString()
            tvOverview.text = tvShow.overview

            var statusFavorite = tvShow.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteTvShow(tvShow, statusFavorite)
                setStatusFavorite(statusFavorite)
                if (statusFavorite) {
                    coordinator.showSnackBar("Added to Favorite")
                } else {
                    coordinator.showSnackBar("Removed from Favorite")
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_border))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}