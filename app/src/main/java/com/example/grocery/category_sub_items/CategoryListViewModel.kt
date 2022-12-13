package com.example.grocery.category_sub_items

import androidx.lifecycle.viewModelScope
import com.example.grocery.base.BaseViewModel
import com.example.grocery.room.CategoryListItems
import com.example.grocery.room.UserDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(private val dataBase: UserDataBase) :
    BaseViewModel() {
    private val _imageEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addImageEventChannel = _imageEventChannel.receiveAsFlow()

    private val _addCategoryEventChannel = Channel<Unit>(Channel.CONFLATED)
    val addCategoryEventChannel = _addCategoryEventChannel.receiveAsFlow()

    private val _categoryEventChannel = Channel<Unit>(Channel.CONFLATED)
    val categoryEventChannel = _categoryEventChannel.receiveAsFlow()


    private val _toastEventChannel = Channel<String>(Channel.CONFLATED)
    val toastEventChannel = _toastEventChannel.receiveAsFlow()
    val showImage = MutableStateFlow(false)

    val categoryName = MutableStateFlow("")
    val categoryNameError = MutableStateFlow("")
    val categoryNameErrorEnabled = MutableStateFlow(false)
    val categoryImagePath = MutableStateFlow("")
    val categoryImagePathError = MutableStateFlow("")
    val categoryImagePathErrorEnabled = MutableStateFlow(false)
    val categoryitemName = MutableStateFlow("")
    val categoryitemNameError = MutableStateFlow("")
    val categoryitemNameErrorEnabled = MutableStateFlow(false)
    val categoryitemPrice = MutableStateFlow("")
    val categoryitemPriceError = MutableStateFlow("")
    val categoryitemPriceErrorEnabled = MutableStateFlow(false)
    val categoryitemWeight = MutableStateFlow("")
    val categoryitemWeightError = MutableStateFlow("")
    val categoryitemWeightErrorEnabled = MutableStateFlow(false)
    val categoryitemDesc = MutableStateFlow("")
    val categoryitemDescError = MutableStateFlow("")
    val categoryitemDescErrorEnabled = MutableStateFlow(false)

    val selectCategoryName = MutableStateFlow("")
    val selectCategoryNameError = MutableStateFlow("")
    val selectCategoryNameErrorEnabled = MutableStateFlow(false)

    var categoryList = MutableStateFlow(listOf<String>())

    val categoryDealsTypeList = listOf(
        "BEST_SELLER_SQUAD",
        "FOOD_DRINKS",
        "COOKING_ESSENTIAL_CORNER",
        "FRUITS_VEGGIES",
        "FREQUENTLY_BOUGHT",
        "GROOMING_ESSENTIAL",
        "SPONSORED_ITEMS"
    )
    var categorySubItems = MutableStateFlow(listOf<CategoryListItems>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryList.value = dataBase.userDao().getCategory().map {
                it.categoryName
            }
            categorySubItems.value = dataBase.userDao().getCategoryList()
        }
    }


    fun onBrowseButtonClicked() {
        _imageEventChannel.trySend(Unit)
    }

    fun onCategoryAddButtonClicked() {
        _categoryEventChannel.trySend(Unit)
    }

    private fun addCategoryList() {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                dataBase.userDao().addCategoryList(
                    CategoryListItems(
                        categoryName = categoryName.value,
                        categoryItemName = categoryitemName.value,
                        categoryImagePath = categoryImagePath.value,
                        categoryItemDesc = categoryitemDesc.value,
                        categoryItemPrice = categoryitemPrice.value.toInt(),
                        categoryItemWeight = categoryitemWeight.value.toInt(),
                        categoryDealType = selectCategoryName.value
                    )
                )
                _toastEventChannel.trySend("successfully created")
                _addCategoryEventChannel.trySend(Unit)
            } catch (e: java.lang.Exception) {
                e.localizedMessage?.let { _toastEventChannel.trySend(it) }
            }
        }

    }

    fun submitButtonClicked() {
        if (categoryName.value.isEmpty()) {
            categoryNameError.value = "Name Field Required!"
            categoryNameErrorEnabled.value = true
        }
        if (categoryitemName.value.isEmpty()) {
            categoryitemNameError.value = "Item Name Field Required!"
            categoryitemNameErrorEnabled.value = true
        }
        if (categoryitemPrice.value.isEmpty()) {
            categoryitemPriceError.value = "Price Field Required!"
            categoryitemPriceErrorEnabled.value = true
        }
        if (categoryitemWeight.value.isEmpty()) {
            categoryitemWeightError.value = "Weight Field Required!"
            categoryitemWeightErrorEnabled.value = true
        }
        if (categoryitemDesc.value.isEmpty()) {
            categoryitemDescError.value = "Desc Field Required!"
            categoryitemDescErrorEnabled.value = true
        }
        if (categoryImagePath.value.isEmpty()) {
            categoryImagePathError.value = "Image Field Required!"
            categoryImagePathErrorEnabled.value = true
        }
        if (selectCategoryName.value.isEmpty()) {
            selectCategoryNameError.value = "Category Sub Type Field Required!"
            selectCategoryNameErrorEnabled.value = true
        } else {
            categoryNameErrorEnabled.value = false
            categoryitemNameErrorEnabled.value = false
            categoryitemPriceError.value = ""
            categoryitemPriceErrorEnabled.value = false
            categoryitemWeightError.value = ""
            categoryitemWeightErrorEnabled.value = false
            categoryitemDescError.value = ""
            categoryitemDescErrorEnabled.value = false
            categoryImagePathError.value = ""
            categoryImagePathErrorEnabled.value = false
            addCategoryList()
        }
    }

    override fun refreshData() {
        getData()
    }


}