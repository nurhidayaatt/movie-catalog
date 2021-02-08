package com.nurhidayaatt.moviecatalog.presentation.movie

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
import com.nurhidayaatt.core.presentation.adapter.MovieAdapter
import com.nurhidayaatt.moviecatalog.databinding.MovieFragmentBinding
import com.nurhidayaatt.moviecatalog.utils.showSnackBar
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.scope.viewModel
import org.koin.core.qualifier.named

class MovieFragment : Fragment() {

    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding
    private val viewModelScope = getKoin().getOrCreateScope("Scope1", named("ViewModel"))
    private val viewModel: MovieViewModel by viewModelScope.viewModel(this)
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFilter()
        viewModel.movie.observe(viewLifecycleOwner) { response ->
            binding?.let { binding ->
                when (response) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        response.data?.let {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(it)
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
        movieAdapter = MovieAdapter()
        binding?.let {
            with(it.recyclerMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

        movieAdapter.setOnItemClickListener {
            val action = MovieFragmentDirections.actionNavigationMovieToNavigationDetail(movie = it)
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