package com.example.grocery.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int = 0,
    @ColumnInfo(name = "category_name")
    val categoryName: String = "",
    @ColumnInfo(name = "category_image_path")
    val categoryImagePath: String = ""
)

@Parcelize
@Entity
data class CategoryListItems(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int = 0,
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "category_deal_type")
    val categoryDealType: String,
    @ColumnInfo(name = "category_image_path")
    val categoryImagePath: String,
    @ColumnInfo(name = "category_item_price")
    val categoryItemPrice: Int,
    @ColumnInfo(name = "discount")
    val discount: Int = 0,
    @ColumnInfo(name = "category_item_weight")
    var categoryItemWeight: Int,
    @ColumnInfo(name = "category_item_desc")
    val categoryItemDesc: String,
    @ColumnInfo(name = "category_item_name")
    val categoryItemName: String,
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,
    @ColumnInfo(name = "max_allowed_quantity")
    val maxAllowedQuantity: Int = 10,

    ):Parcelable {
    val offerPrice: Int get() = categoryItemPrice - discount

    val isShowAddLayout: Boolean get() = (quantity == 0)
}