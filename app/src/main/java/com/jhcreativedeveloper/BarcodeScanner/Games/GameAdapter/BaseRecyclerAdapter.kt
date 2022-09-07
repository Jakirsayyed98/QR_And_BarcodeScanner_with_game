package com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, I : RecyclerView.ViewHolder> : RecyclerView.Adapter<I>() {
    var list = ArrayList<T>()

    open fun addAllItem(list: List<T>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(t: T) {
        this.list.add(t)
        notifyDataSetChanged()
    }

    open fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}