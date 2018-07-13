package kr.alex.lib.recycler.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer


abstract class RecyclerAdapterViewHolder<D : RecyclerCellType>(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    open fun onBindViewHolder(item: D, position: Int) {}
}