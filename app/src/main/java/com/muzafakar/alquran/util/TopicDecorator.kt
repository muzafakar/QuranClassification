package com.muzafakar.alquran.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muzafakar.alquran.util.xtension.dp

class TopicDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            bottom = 8.dp
            left = 8.dp
            right = 8.dp

            if (parent.getChildAdapterPosition(view) == 0) {
                top = 8.dp
            } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                bottom = 8.dp
            }
        }
    }
}