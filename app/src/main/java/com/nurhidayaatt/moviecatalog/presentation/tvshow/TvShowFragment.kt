package com.nurhidayaatt.moviecatalog.presentation.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurhidayaatt.core.data.source.Resource
import com.nurhidayaatt.core.presentation.SortType
import com.nurhidayaatt.core.presentation.adapter.TvShowAdapter
import com.nurhidayaatt.moviecatalog.databinding.TvShowFragmentBinding
import com.nurhidayaatt.moviecatalog.utils.showSnackBar
import org.koin.android.ext.android.inject

class TvShowFragment : Fragment() {

    private var _binding: TvShowFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel: TvShowViewModel by inject()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFilter()
        viewModel.tvShow.observe(viewLifecycleOwner) { response ->
            binding?.let { binding ->
                when (response) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        response.data?.let {
                            binding.progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(it)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.mainLayout.showSnackBar(response.message.toString())
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        tvShowAdapter = TvShowAdapter()
        binding?.let { binding ->
            with(binding.recyclerTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }

        tvShowAdapter.setOnItemClickListener {
            val action = TvShowFragmentDirections.actionNavigationTvShowToNavigationDetail(tvshow = it)
            findNavController().navigate(action)
        }
    }

    private fun setupFilter() {
        binding?.let { binding ->
            with(binding) {
                viewModel.sortType.observe(viewLifecycleOwner) {
                    spFilter.setSelection(it.ordinal)
                }

                spFilter.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {}

                        override fun onItemSelected(
                            adapterView: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            viewModel.onSortOrderSelected(SortType.values()[position])
                        }
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}