package com.example.grocery.user_home

import androidx.lifecycle.viewModelScope
import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.CategoryListItems
import com.example.grocery.room.UserDataBase
import com.example.grocery.room.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserHomeViewModel @Inject constructor(private val dataBase: UserDataBase) : BaseViewModel() {

    var categoryList = MutableStateFlow(listOf<UserEntity>())
    var categorySubItems = MutableStateFlow(listOf<CategoryListItems>())
    private var list1 = listOf<CategoryListItems>()
    var bestSellerList = MutableStateFlow(list1)
    private var list2 = listOf<CategoryListItems>()
    var foodList = MutableStateFlow(list2)
    private var list3 = listOf<CategoryListItems>()
    var cookingList = MutableStateFlow(list3)
    private var list4 = listOf<CategoryListItems>()
    var fruitsList = MutableStateFlow(list4)
    private var list5 = listOf<CategoryListItems>()
    var frequntlyBoughtList = MutableStateFlow(list5)
    private var list6 = listOf<CategoryListItems>()
    var groomingList = MutableStateFlow(list6)
    private var list7 = listOf<CategoryListItems>()
    var SponsoredList = MutableStateFlow(list7)

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.Default) {
            categoryList.value = dataBase.userDao().getCategory()
            categorySubItems.value = dataBase.userDao().getCategoryList()
            list1 = categorySubItems.value.filter {
                it.categoryDealType.contains("BEST_SELLER_SQUAD")
            }
            list2 = categorySubItems.value.filter {
                it.categoryDealType == "FOOD_DRINKS"
            }
            list3 = categorySubItems.value.filter {
                it.categoryDealType == "COOKING_ESSENTIAL_CORNER"
            }
            list4 = categorySubItems.value.filter {
                it.categoryDealType == "FRUITS_VEGGIES"
            }
            list5 = categorySubItems.value.filter {
                it.categoryDealType == "FREQUENTLY_BOUGHT"
            }
            list6 = categorySubItems.value.filter {
                it.categoryDealType == "GROOMING_ESSENTIAL"
            }
            list7 = categorySubItems.value.filter {
                it.categoryDealType == "SPONSORED_ITEMS"
            }
        }
    }

    override fun refreshData() {
        // TODO("Not yet implemented")
    }

}