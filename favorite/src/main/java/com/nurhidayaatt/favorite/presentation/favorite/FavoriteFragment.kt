package com.nurhidayaatt.favorite.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nurhidayaatt.favorite.R
import com.nurhidayaatt.favorite.databinding.FavoriteFragmentBinding
import com.nurhidayaatt.favorite.presentation.favorite.movie.MovieFavoriteFragment
import com.nurhidayaatt.favorite.presentation.favorite.tvshow.TvShowFavoriteFragment

class FavoriteFragment : Fragment() {

    private var _binding: FavoriteFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SectionsPagerAdapter(this)
        adapter.addFragment(MovieFavoriteFragment(), resources.getString(R.string.movies) )
        adapter.addFragment(TvShowFavoriteFragment(), resources.getString(R.string.tv_shows))

        binding?.let {
            it.viewPager.adapter = adapter
            it.viewPager.currentItem = 0
            TabLayoutMediator(it.tabs, it.viewPager) { tab, position ->
                tab.text = adapter.getTabTitle(position)
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}