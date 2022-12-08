package com.example.grocery.user_home.adapters

import android.content.Context
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ItemHomeAllCategoriesListBinding
import com.example.grocery.room.UserEntity
import com.example.grocery.util.inflate


class AllCategoriesAdapter(
    private val onItemCLicked: (UserEntity) -> (Unit)
) : BaseAdapter<UserEntity>() {
    lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<UserEntity> {
        context = parent.context
        return CategoryViewHolder(parent.inflate(R.layout.item_home_all_categories_list))
    }

    private inner class CategoryViewHolder(binding: ItemHomeAllCategoriesListBinding) :
        BaseViewHolder<ItemHomeAllCategoriesListBinding, UserEntity>(binding) {

        init {
            binding.root.setOnClickListener {
                onItemCLicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: UserEntity) {
            binding.item = item
            try {
                Glide.with(binding.ivCategory.context)
                    .load(item.categoryImagePath)
                    .centerCrop()
                    .error(R.drawable.flipkart_logo)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(binding.ivCategory)
            } catch (_: Exception) {

            }
        }
    }

}