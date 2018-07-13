package kr.alex.lib.recycler.adapter


sealed class RecyclerAdapterChangeEvent<D : RecyclerCellType> {
    data class Reloaded<D : RecyclerCellType>(val newList: List<D>) : RecyclerAdapterChangeEvent<D>()
    data class Removed<D : RecyclerCellType>(val index: Int) : RecyclerAdapterChangeEvent<D>()
    data class RemovedRange<D : RecyclerCellType>(val startIndex: Int, val itemCount: Int) : RecyclerAdapterChangeEvent<D>()
    data class Inserted<D : RecyclerCellType>(val index: Int, val item: D) : RecyclerAdapterChangeEvent<D>()
    data class InsertedRange<D : RecyclerCellType>(val index: Int, val items: List<D>) : RecyclerAdapterChangeEvent<D>()
    data class Changed<D : RecyclerCellType>(val index: Int, val item: D) : RecyclerAdapterChangeEvent<D>()
    data class ChangedRange<D : RecyclerCellType>(val index: Int, val items: List<D>) : RecyclerAdapterChangeEvent<D>()
}