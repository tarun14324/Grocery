package com.example.grocery.user_home.adapters

import android.view.ViewGroup
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ItemCardBannersBinding
import com.example.grocery.room.CategoryListItems
import com.example.grocery.util.inflate


class ImageAdapter(private val onItemClicked: (CategoryListItems) -> Unit) :
    BaseAdapter<CategoryListItems>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<CategoryListItems> {
        return ImageViewHolder(parent.inflate(R.layout.item_card_banners))
    }

    inner class ImageViewHolder(binding: ItemCardBannersBinding) :
        BaseViewHolder<ItemCardBannersBinding, CategoryListItems>(binding) {
        override fun onBind(item: CategoryListItems) {
            binding.item = item
            binding.cardView.setOnClickListener {
                onItemClicked(item)
            }

        }

    }
}
