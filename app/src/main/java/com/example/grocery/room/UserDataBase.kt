package com.example.grocery.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [UserEntity::class,CategoryListItems::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    version = 3,
    exportSchema = true
)
abstract class UserDataBase() : RoomDatabase() {
    abstract fun userDao(): RoomDao
}