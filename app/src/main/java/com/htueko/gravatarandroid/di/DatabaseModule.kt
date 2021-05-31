package com.htueko.gravatarandroid.di

import android.content.Context
import androidx.room.Room
import com.htueko.gravatarandroid.data.local.LocalConstant
import com.htueko.gravatarandroid.data.local.database.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    // to provide database
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PersonDatabase = Room.databaseBuilder(
        context,
        PersonDatabase::class.java,
        LocalConstant.DB_NAME
    ).build()

    // to provide dao
    @Singleton
    @Provides
    fun providePersonDao(db: PersonDatabase) = db.getPersonDao()

}