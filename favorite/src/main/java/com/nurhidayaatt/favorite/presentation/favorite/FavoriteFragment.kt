package com.nurhidayaatt.favorite.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nurhidayaatt.favorite.databinding.FavoriteFragmentBinding

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
        val sectionsPagerAdapter: SectionsPagerAdapter? = context?.let {
            SectionsPagerAdapter(
                it,
                childFragmentManager
            )
        }
        binding?.let { binding ->
            binding.viewPager.adapter = sectionsPagerAdapter
            binding.tabs.setupWithViewPager(binding.viewPager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}