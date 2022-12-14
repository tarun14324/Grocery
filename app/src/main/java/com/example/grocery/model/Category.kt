package com.example.grocery.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int = 0,
    val name: String = "",
    val isUpdateCategory: Boolean = false
):Parcelable
