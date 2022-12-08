package com.example.grocery.user_home.adapters

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.grocery.R
import com.example.grocery.base.BaseAdapter
import com.example.grocery.base.BaseHolder
import com.example.grocery.base.BaseViewHolder
import com.example.grocery.databinding.ProductItemGridBinding
import com.example.grocery.model.ProductDetails
import com.example.grocery.model.SingleProductGrid
import com.example.grocery.util.inflate


class DealsAdapter(
    private val onItemCLicked: (SingleProductGrid) -> (Unit),
) : BaseAdapter<SingleProductGrid>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseHolder<SingleProductGrid> =
        DealsViewHolder(parent.inflate(R.layout.product_item_grid))


    inner class DealsViewHolder(binding: ProductItemGridBinding) :
        BaseViewHolder<ProductItemGridBinding, SingleProductGrid>(binding) {
        init {
            binding.root.setOnClickListener {
                onItemCLicked(getItem(adapterPosition))
            }
        }

        override fun onBind(item: SingleProductGrid) {
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
                Glide.with(binding.ivProduct.context)
                    .load(item.productImage)
                    .centerCrop()
                    .error(R.drawable.flipkart_logo)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(binding.ivProduct)
            } catch (_: Exception) {

            }
        }
    }
}
