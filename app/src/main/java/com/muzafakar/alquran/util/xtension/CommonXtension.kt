package com.muzafakar.alquran.util.xtension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder

fun RecyclerView.init(adapter: GroupAdapter<GroupieViewHolder>, decorator: RecyclerView.ItemDecoration) {
    this.layoutManager = LinearLayoutManager(this.context)
    this.adapter = adapter
    this.addItemDecoration(decorator)
}