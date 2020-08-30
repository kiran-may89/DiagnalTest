package com.diagnal.test.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView


class ItemDecoration(private val offset:Int):RecyclerView.ItemDecoration() {
    constructor( context: Context, @DimenRes offset: Int):this(offset) {
        context.resources.getDimensionPixelSize(offset)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(offset,offset*2,offset,offset*2)
    }
}