package com.example.grocery.di

import android.content.Context
import androidx.room.Room
import com.example.grocery.room.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): UserDataBase {
        return Room.databaseBuilder(context, UserDataBase::class.java, "category")
            .fallbackToDestructiveMigration()
            .build()
    }
}