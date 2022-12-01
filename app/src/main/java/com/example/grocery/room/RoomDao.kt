package com.example.grocery.room

import androidx.room.*

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: UserEntity)

    @Update
    suspend fun editCategory(category: UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY category_name DESC")
    fun getCategory(): List<UserEntity>

}
