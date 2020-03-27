package com.muzafakar.alquran.view


import android.os.Bundle
import android.text.Layout
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.switchmaterial.SwitchMaterial
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
import kotlinx.android.synthetic.main.layout_verse_setting.*
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
        setHasOptionsMenu(true)
        (activity!! as MainActivity).setSupportActionBar(tbVerse)

        val imageUrl = if (prefManager.topicAsset.isNotEmpty()) {
            prefManager.topicAsset[topic.id - 1]
        } else {
            topicDefaulAsset.toList()[topic.id - 1].second
        }
        Glide.with(this).load(imageUrl).centerCrop().into(imgTopic)
        ctlVerse.title = topic.name.wordCaps()


        rvVerse.init(verseAdp, DividerItemDecoration(context!!, RecyclerView.VERTICAL))

        populateVerse()
    }

    private fun populateVerse() {
        quranViewModel.getQuranByTopicId(topic.id).observe(viewLifecycleOwner, Observer {
            val verseViewHolder = it.map { v ->
                VerseViewHolder(v, prefManager.isShowTopic) { _ ->
                    Timber.i(v.toString())
                }
            }

            with(verseAdp) {
                clear()
                addAll(verseViewHolder)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_verse, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity!!.onBackPressed()
            R.id.menuSettingVerse -> setting()
        }
        return true
    }

    private fun setting() {
        val dialog = BottomSheetDialog(context!!).apply {
            setContentView(R.layout.layout_verse_setting)
            val switch = findViewById<SwitchMaterial>(R.id.switchTopic)
            switch?.isChecked = prefManager.isShowTopic
            switch?.setOnCheckedChangeListener { _, isChecked ->
                prefManager.isShowTopic = isChecked
                populateVerse()
                dismiss()
            }
        }

        dialog.show()
    }

}
