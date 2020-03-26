package com.muzafakar.alquran.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muzafakar.alquran.R
import com.muzafakar.alquran.model.Topic
import com.muzafakar.alquran.util.PrefManager
import com.muzafakar.alquran.util.topicDefaulAsset
import com.muzafakar.alquran.util.xtension.init
import com.muzafakar.alquran.util.xtension.wordCaps
import com.muzafakar.alquran.view.viewholder.VerseViewHolder
import com.muzafakar.alquran.viewmodel.QuranViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_topic.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class TopicFragment : Fragment() {
    private val quranViewModel: QuranViewModel by viewModel()
    private val argument: TopicFragmentArgs by navArgs()
    private val prefManager: PrefManager by inject()

    private val verseAdp = GroupAdapter<GroupieViewHolder>()

    private lateinit var topic: Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topic = argument.topic
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_topic, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUrl = if (prefManager.topicAsset.isNotEmpty()) {
            prefManager.topicAsset[topic.id - 1]
        } else {
            topicDefaulAsset.toList()[topic.id - 1].second
        }
        Glide.with(this).load(imageUrl).centerCrop().into(imgTopic)
        tvTopicName.text = topic.name.wordCaps()

        rvVerse.init(verseAdp, DividerItemDecoration(context!!, RecyclerView.VERTICAL))


        quranViewModel.getQuranByTopicId(topic.id).observe(viewLifecycleOwner, Observer {
            val verseViewHolder = it.map { v ->
                VerseViewHolder(v) { _ ->
                    Timber.i(v.toString())
                }
            }

            with(verseAdp) {
                clear()
                addAll(verseViewHolder)
            }

        })
    }


}
