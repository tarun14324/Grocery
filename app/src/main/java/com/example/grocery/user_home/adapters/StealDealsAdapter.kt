package com.example.grocery.user_home.adapters

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ListStealDealsProductsBinding
import com.example.grocery.model.ProductDetails
import com.example.grocery.util.inflate

class StealDealsAdapter(
    private val onItemCLicked: (ProductDetails) -> (Unit),
) : BaseAdapter<ProductDetails>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<ProductDetails> =
        StealDealsViewHolder(parent.inflate(R.layout.list_steal_deals_products))


    inner class StealDealsViewHolder(binding: ListStealDealsProductsBinding) :
        BaseViewHolder<ListStealDealsProductsBinding, ProductDetails>(binding) {
        init {
            binding.root.setOnClickListener {
                onItemCLicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: ProductDetails) {
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
            try {
                Glide.with(binding.productImg.context)
                    .load(item.categoryImagePath)
                    .centerCrop()
                    .error(R.drawable.flipkart_logo)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(binding.productImg)
            } catch (_: Exception) {

            }
        }

    }
}

