package com.example.grocery.model

data class SingleProductGrid(
    val productName: String,
    val productDesc: String,
    val productImage: String,
    val productPrice: Int,
    val discount: Int,
    var quantity: Int = 0,
    val maxAllowedQuantity: Int = 10,
){
    val offerPrice: Int get() = productPrice - discount

    val isShowAddLayout: Boolean get() = (quantity == 0)
}