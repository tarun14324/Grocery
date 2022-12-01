package com.example.grocery.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [UserEntity::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ],
    version = 2,
    exportSchema = true
)
abstract class  UserDataBase(): RoomDatabase() {
   abstract fun userDao():RoomDao
}