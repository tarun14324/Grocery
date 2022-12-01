package com.example.grocery.base

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<Item> : RecyclerView.Adapter<BaseHolder<Item>>() {

    var listItems: ArrayList<Item> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    open fun submitList(data: List<Item>?) {
        listItems.clear()
        if (data != null) {
            listItems.addAll(ArrayList(data))
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: BaseHolder<Item>, position: Int) {

        val item = getItem(position)

        if (item != null) {
            viewHolder.onBind(item)
        }

    }
    open fun getItem(position: Int): Item {
        return listItems[position]
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}

abstract class BaseHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: T)

}

abstract class BaseViewHolder<Binding : ViewDataBinding, Item>(val binding: Binding) :
    BaseHolder<Item>(binding.root)


