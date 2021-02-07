package com.nurhidayaatt.favorite.presentation.favorite

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nurhidayaatt.favorite.R
import com.nurhidayaatt.favorite.presentation.favorite.movie.MovieFavoriteFragment
import com.nurhidayaatt.favorite.presentation.favorite.tvshow.TvShowFavoriteFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val title = intArrayOf(R.string.movies, R.string.tv_shows)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFavoriteFragment()
            1 -> fragment = TvShowFavoriteFragment()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(title[position])
    }

    override fun getCount(): Int {
        return 2
    }
}