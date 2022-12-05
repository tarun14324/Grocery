package com.example.grocery.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

@Entity
data class CategoryListItems(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int = 0,
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "category_image_path")
    val categoryImagePath: String,
    @ColumnInfo(name = "category_item_price")
    val categoryItemPrice: Int,
    @ColumnInfo(name = "category_item_weight")
    val categoryItemWeight: String,
    @ColumnInfo(name = "category_item_desc")
    val categoryItemDesc: String,
    @ColumnInfo(name = "category_item_name")
    val categoryItemName: String,
) {
    val discountPrice
        get() = categoryItemPrice + 20
}