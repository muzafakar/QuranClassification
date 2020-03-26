package com.muzafakar.alquran.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.muzafakar.alquran.view.DiscoverFragment
import com.muzafakar.alquran.view.QuranFragment

/**
 * Created by muzafakar at 25/03/20
 */
class HomeAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = listOf(
        "Baca Quran" to QuranFragment(),
        "Discover" to DiscoverFragment()
    )

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = fragments[position].first

    override fun getItem(position: Int): Fragment = fragments[position].second
}