package com.example.grocery.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grocery.category.CategoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(context, RoomDatabase::class.java, "category").build()
    }
}