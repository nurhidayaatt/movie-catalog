package com.nurhidayaatt.favorite.presentation.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhidayaatt.core.data.source.Resource
import com.nurhidayaatt.core.presentation.adapter.MovieAdapter
import com.nurhidayaatt.favorite.databinding.FragmentMovieFavoriteBinding
import com.nurhidayaatt.favorite.presentation.favorite.FavoriteFragmentDirections
import com.nurhidayaatt.favorite.presentation.favorite.FavoriteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MovieFavoriteFragment : Fragment() {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding
    private val viewModel: FavoriteViewModel by sharedViewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        viewModel.movies.observe(viewLifecycleOwner) { response ->
            binding?.let { binding ->
                when (response) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        response.data?.let {
                            if (!it.isNullOrEmpty()) {
                                binding.tvEmptyData.visibility = View.GONE
                                movieAdapter.submitList(it)
                                movieAdapter.notifyDataSetChanged()
                            } else {
                                binding.tvEmptyData.visibility = View.VISIBLE
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()
        binding?.let { binding ->
            with(binding.recyclerMovieFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

        movieAdapter.setOnItemClickListener {
            val action = FavoriteFragmentDirections.actionNavigationFavoriteToNavigationDetail(movie = it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}