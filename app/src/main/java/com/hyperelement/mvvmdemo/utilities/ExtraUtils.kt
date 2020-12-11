package com.hyperelement.mvvmdemo.utilities

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import smartadapter.extension.SmartViewHolderBinder
import smartadapter.viewevent.model.ViewEvent
import smartadapter.viewevent.swipe.BasicSwipeEventBinder
import smartadapter.viewevent.swipe.SwipeFlags


object ExtraUtils {

    //Ref: SmartRecyclerAdapter from manneohlund https://github.com/manneohlund/
    class SwipeRemoveItemBinder(
        override var swipeFlags: SwipeFlags,
        override var eventListener: (ViewEvent.OnItemSwiped) -> Unit
    ) : BasicSwipeEventBinder(
        eventListener = eventListener
    ), SmartViewHolderBinder {
        override fun onChildDraw(
            canvas: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            val itemView = viewHolder.itemView
            val icon = ContextCompat.getDrawable(
                viewHolder.itemView.context,
                android.R.drawable.ic_menu_delete
            )
            val background =
                ColorDrawable(Color.RED)

            val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
            val iconTop = itemView.top + (itemView.height - icon.intrinsicHeight) / 2
            val iconBottom = iconTop + icon.intrinsicHeight

            val iconLeft = itemView.right - iconMargin - icon.intrinsicWidth
            val iconRight = itemView.right - iconMargin

            icon.setBounds(
                iconLeft,
                iconTop,
                iconRight,
                iconBottom
            )

            background.setBounds(
                (itemView.right + dX).toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )

            if (dX.toInt() == 0) { // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }

            background.draw(canvas)

            if (-dX > (icon.intrinsicWidth + iconMargin)) // Draw icon only on full visibility
                icon.draw(canvas)
        }
    }
}