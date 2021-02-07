package com.nurhidayaatt.favorite.presentation.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhidayaatt.core.data.source.Resource
import com.nurhidayaatt.core.presentation.adapter.TvShowAdapter
import com.nurhidayaatt.favorite.databinding.FragmentTvShowFavoriteBinding
import com.nurhidayaatt.favorite.presentation.favorite.FavoriteFragmentDirections
import com.nurhidayaatt.favorite.presentation.favorite.FavoriteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class TvShowFavoriteFragment : Fragment() {

    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding
    private val viewModel: FavoriteViewModel by sharedViewModel()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        viewModel.tvShows.observe(viewLifecycleOwner) { response ->
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
                                tvShowAdapter.submitList(it)
                                tvShowAdapter.notifyDataSetChanged()
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
        tvShowAdapter = TvShowAdapter()
        binding?.let { binding ->
            with(binding.recyclerTvShowFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }

        tvShowAdapter.setOnItemClickListener {
            val action =
                FavoriteFragmentDirections.actionNavigationFavoriteToNavigationDetail(
                    tvshow = it
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}