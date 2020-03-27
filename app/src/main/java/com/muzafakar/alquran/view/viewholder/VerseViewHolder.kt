package com.muzafakar.alquran.view.viewholder

import android.content.Context
import android.view.View
import com.google.android.material.chip.Chip
import com.muzafakar.alquran.R
import com.muzafakar.alquran.model.Verse
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_verse.view.*

class VerseViewHolder(
        private val verse: Verse,
        private val isShowTopic: Boolean,
        private val click: (Verse) -> Unit
) : Item() {
    override fun getLayout(): Int = R.layout.item_verse

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder.itemView) {
            setOnClickListener { click(verse) }

            tvTranslation.text = verse.translation
            if (isShowTopic) {
                cgTopic.visibility = View.VISIBLE
                cgTopic.removeAllViews()
                generateTopicChips(this.context).forEach {
                    cgTopic.addView(it)
                }
            } else {
                cgTopic.visibility = View.GONE
            }
        }
    }

    private fun generateTopicChips(context: Context): MutableList<Chip> {
        val chips = mutableListOf<Chip>()

        if (verse.akhirat == 1) {
            val newChip = Chip(context).apply {
                text = "Alam Akhirat"
            }
            chips.add(newChip)
        }

        if (verse.akhlak == 1) {
            val newChip = Chip(context).apply {
                text = "Akhlak"
            }
            chips.add(newChip)
        }

        if (verse.aqidah == 1) {
            val newChip = Chip(context).apply {
                text = "Aqidah"
            }
            chips.add(newChip)
        }

        if (verse.dunia == 1) {
            val newChip = Chip(context).apply {
                text = "Alam Dunia"
            }
            chips.add(newChip)
        }

        if (verse.syariah == 1) {
            val newChip = Chip(context).apply {
                text = "Syariah"
            }
            chips.add(newChip)
        }

        if (verse.ilmu == 1) {
            val newChip = Chip(context).apply {
                text = "Ilmu"
            }
            chips.add(newChip)
        }

        if (verse.kisah == 1) {
            val newChip = Chip(context).apply {
                text = "Kisah"
            }
            chips.add(newChip)
        }

        if (verse.ghaib == 1) {
            val newChip = Chip(context).apply {
                text = "Alam Ghaib"
            }
            chips.add(newChip)
        }

        return chips
    }

}