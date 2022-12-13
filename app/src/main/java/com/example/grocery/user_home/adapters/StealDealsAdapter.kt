package com.example.grocery.user_home.adapters

import android.view.ViewGroup
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ListStealDealsProductsBinding
import com.example.grocery.room.CategoryListItems
import com.example.grocery.util.inflate

class StealDealsAdapter(
    private val onItemCLicked: (CategoryListItems) -> (Unit),
) : BaseAdapter<CategoryListItems>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<CategoryListItems> =
        StealDealsViewHolder(parent.inflate(R.layout.list_steal_deals_products))


    inner class StealDealsViewHolder(binding: ListStealDealsProductsBinding) :
        BaseViewHolder<ListStealDealsProductsBinding, CategoryListItems>(binding) {
        init {
            binding.root.setOnClickListener {
                onItemCLicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: CategoryListItems) {
            binding.item = item
            binding.decCountButton.setOnClickListener {
                if (item.categoryItemWeight > 0) {
                    item.categoryItemWeight--
                }
                notifyItemChanged(layoutPosition)
            }
            binding.incCountButton.setOnClickListener {
                if (item.categoryItemWeight < item.maxAllowedQuantity) {
                    item.categoryItemWeight++
                }
                notifyItemChanged(layoutPosition)
            }
            binding.addButtonLayout.setOnClickListener {
                binding.incCountButton.performClick()
            }
        }

    }
}

