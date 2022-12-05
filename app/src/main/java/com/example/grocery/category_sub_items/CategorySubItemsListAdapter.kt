package com.example.grocery.category_sub_items

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ItemAddCategoryListBinding
import com.example.grocery.room.CategoryListItems
import com.example.grocery.util.inflate


class CategorySubItemsListAdapter(
    private val onItemCLicked: (CategoryListItems) -> (Unit)
) : BaseAdapter<CategoryListItems>() {
    lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<CategoryListItems> {
        context = parent.context
        return CategoryViewHolder(parent.inflate(R.layout.item_add_category_list))
    }

    private inner class CategoryViewHolder(binding: ItemAddCategoryListBinding) :
        BaseViewHolder<ItemAddCategoryListBinding, CategoryListItems>(binding) {

        init {
            binding.root.setOnClickListener {
                onItemCLicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: CategoryListItems) {
            binding.item = item
            try {
            Glide.with(context)
                .load(item.categoryImagePath)
                .error(R.drawable.ic_person)
                .into(binding.itemImage)
        }catch (_:Exception){

        }
        }
    }

}
