package com.example.grocery.category

import android.view.ViewGroup
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ItemAddCategoryBinding
import com.example.grocery.room.UserEntity
import com.example.grocery.util.inflate

class CategoryAdapter(
    private val onEditClicked: (UserEntity) -> (Unit)
) : BaseAdapter<UserEntity>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<UserEntity> =
        CategoryViewHolder(parent.inflate(R.layout.item_add_category))

    private inner class CategoryViewHolder(binding: ItemAddCategoryBinding) :
        BaseViewHolder<ItemAddCategoryBinding, UserEntity>(binding) {

        init {
            binding.itemEdit.setOnClickListener {
                onEditClicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: UserEntity) {
            binding.item = item
        }

    }

}
