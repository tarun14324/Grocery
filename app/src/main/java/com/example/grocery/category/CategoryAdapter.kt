package com.example.grocery.category

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ItemAddCategoryBinding
import com.example.grocery.room.UserEntity
import com.example.grocery.util.inflate
import java.io.File


class CategoryAdapter(
    private val onEditClicked: (UserEntity) -> (Unit)
) : BaseAdapter<UserEntity>() {
    lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<UserEntity> {
        context = parent.context
      return  CategoryViewHolder(parent.inflate(R.layout.item_add_category))
    }
    private inner class CategoryViewHolder(binding: ItemAddCategoryBinding) :
        BaseViewHolder<ItemAddCategoryBinding, UserEntity>(binding) {

        init {
            binding.itemEdit.setOnClickListener {
                onEditClicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: UserEntity) {
            binding.item = item
            val uri = Uri.parse(item.categoryImagePath)
            Glide.with(context)
                .load(uri.path?.let { File(it) })
                .into(binding.itemImage)

        }
    }

}
