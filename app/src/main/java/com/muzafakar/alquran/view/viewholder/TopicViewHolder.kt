package com.muzafakar.alquran.view.viewholder

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.muzafakar.alquran.R
import com.muzafakar.alquran.model.Topic
import com.muzafakar.alquran.model.TopicEnum
import com.muzafakar.alquran.util.xtension.wordCaps
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_topik.view.*
import timber.log.Timber

/**
 * Created by muzafakar at 25/03/20
 */
class TopicViewHolder(
    private val topic: Topic,
    private val imageUrl: String,
    private val click: () -> Unit
) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.let {
            it.setOnClickListener { click() }
            it.tvTopicName.text = topic.name.wordCaps()

            val requestOption = RequestOptions().apply {
                transform(CenterCrop(), RoundedCorners(14))
            }

            Glide.with(it).load(imageUrl).apply(requestOption).into(it.imgTopic)
        }
    }

    override fun getLayout(): Int = R.layout.item_topik
}