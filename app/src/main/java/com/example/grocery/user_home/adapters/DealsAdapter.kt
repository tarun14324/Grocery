package com.example.grocery.user_home.adapters

import android.view.ViewGroup
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ProductItemGridBinding
import com.example.grocery.room.CategoryListItems
import com.example.grocery.util.inflate


class DealsAdapter(
    private val onItemCLicked: (CategoryListItems) -> (Unit),
) : BaseAdapter<CategoryListItems>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<CategoryListItems> =
        DealsViewHolder(parent.inflate(R.layout.product_item_grid))


    inner class DealsViewHolder(binding: ProductItemGridBinding) :
        BaseViewHolder<ProductItemGridBinding, CategoryListItems>(binding) {
        init {
            binding.root.setOnClickListener {
                onItemCLicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: CategoryListItems) {
            binding.item = item
            binding.decCountButton.setOnClickListener {
                if (item.quantity > 0) {
                    item.quantity--
                }
                notifyItemChanged(layoutPosition)
            }
            binding.incCountButton.setOnClickListener {
                if (item.quantity < item.maxAllowedQuantity) {
                    item.quantity++
                }
                notifyItemChanged(layoutPosition)
            }
            binding.addButtonLayout.setOnClickListener {
                binding.incCountButton.performClick()
            }
        }
    }
}
