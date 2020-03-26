package com.muzafakar.alquran.view


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzafakar.alquran.R
import com.muzafakar.alquran.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var homeAdapter: HomeAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeAdapter = HomeAdapter(childFragmentManager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpHome.adapter = homeAdapter

        bnvHome.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuQuran -> vpHome.setCurrentItem(0, true)
                R.id.menuDiscover -> vpHome.setCurrentItem(1, true)
            }

            true
        }
    }


}
