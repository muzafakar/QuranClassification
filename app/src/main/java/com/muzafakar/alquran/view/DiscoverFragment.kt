package com.muzafakar.alquran.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.muzafakar.alquran.R
import com.muzafakar.alquran.model.TopicEnum
import com.muzafakar.alquran.util.PrefManager
import com.muzafakar.alquran.util.TopicDecorator
import com.muzafakar.alquran.util.topicDefaulAsset
import com.muzafakar.alquran.util.xtension.init
import com.muzafakar.alquran.view.viewholder.TopicViewHolder
import com.muzafakar.alquran.viewmodel.QuranViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_discover.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment() {
    private val quranViewModel: QuranViewModel by viewModel()
    private val topicAdapter = GroupAdapter<GroupieViewHolder>()
    private val prefManager: PrefManager by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_discover, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTopic.init(topicAdapter, TopicDecorator())

        quranViewModel.getTopics().observe(viewLifecycleOwner, Observer {

            val topic = it.map { t ->
                val url = if (prefManager.topicAsset.isNotEmpty()) {
                    prefManager.topicAsset[t.id - 1]
                } else {
                    topicDefaulAsset.toList()[t.id - 1].second
                }
                TopicViewHolder(t, url) {
                    val action = HomeFragmentDirections.actionHomeFragmentToTopicFragment(t)
                    findNavController().navigate(action)
                }
            }

            with(topicAdapter) {
                clear()
                addAll(topic)
            }
        })
    }


}
