package kr.alex.lib.recycler.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


class RecyclerAdapter<D : RecyclerCellType>(private val delegate: Delegate<D>) : RecyclerView.Adapter<RecyclerAdapterViewHolder<D>>() {
    private val dataList = mutableListOf<D>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterViewHolder<D> = delegate.createViewHolderForCellStyle(parent, viewType)

    override fun getItemCount(): Int = dataList.count()

    override fun onBindViewHolder(holder: RecyclerAdapterViewHolder<D>, position: Int) {
        holder.onBindViewHolder(dataList[position], position)
    }

    override fun getItemViewType(position: Int): Int = delegate.getCellStyle(position, dataList[position])

    fun onReceivedRecyclerEvent(event: RecyclerAdapterChangeEvent<D>) {
        when (event) {
            is RecyclerAdapterChangeEvent.Reloaded -> {
                dataList.clear()
                dataList.addAll(event.newList)
                notifyDataSetChanged()
            }
            is RecyclerAdapterChangeEvent.Removed -> {
                dataList.removeAt(event.index)
                notifyItemRemoved(event.index)
            }
            is RecyclerAdapterChangeEvent.RemovedRange -> {
                for (i in (event.itemCount - 1) downTo 0) {
                    dataList.removeAt(i + event.startIndex)
                }
                notifyItemRangeRemoved(event.startIndex, event.itemCount)
            }
            is RecyclerAdapterChangeEvent.Inserted -> {
                dataList.add(event.index, event.item)
                notifyItemInserted(event.index)
            }
            is RecyclerAdapterChangeEvent.InsertedRange -> {
                dataList.addAll(event.index, event.items)
                notifyItemRangeInserted(event.index, event.items.count())
            }
            is RecyclerAdapterChangeEvent.Changed -> {
                dataList[event.index] = event.item
                notifyItemChanged(event.index)
            }
            is RecyclerAdapterChangeEvent.ChangedRange -> {
                for (i in 0 until event.items.count()) {
                    dataList[event.index + i] = event.items[i]
                }
                notifyItemRangeChanged(event.index, event.items.count())
            }
        }
    }

    interface Delegate<D : RecyclerCellType> {
        fun getCellStyle(position: Int, item: D): Int
        fun createViewHolderForCellStyle(parent: ViewGroup, viewType: Int): RecyclerAdapterViewHolder<D>
    }
}