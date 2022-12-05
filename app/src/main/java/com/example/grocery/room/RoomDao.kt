package com.example.grocery.room

import androidx.room.*

@Dao
interface RoomDao {
    // for categories
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: UserEntity)

    @Update
    suspend fun editCategory(category: UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY category_name DESC")
    fun getCategory(): List<UserEntity>

    // for categories list
    @Query("SELECT * FROM categorylistitems ORDER BY category_name DESC")
    fun getCategoryList(): List<CategoryListItems>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategoryList(category: CategoryListItems)

}
