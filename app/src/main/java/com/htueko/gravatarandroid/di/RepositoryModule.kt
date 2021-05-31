package com.htueko.gravatarandroid.di

import com.htueko.gravatarandroid.data.datasource.LocalDataSource
import com.htueko.gravatarandroid.data.datasource.LocalDataSourceImpl
import com.htueko.gravatarandroid.data.datasource.RemoteDataSource
import com.htueko.gravatarandroid.data.datasource.RemoteDataSourceImpl
import com.htueko.gravatarandroid.repository.AppRepository
import com.htueko.gravatarandroid.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // to provide remote data source
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource


    // to provide local database data source
    @Binds
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource


    // to provide repository
    @Binds
    abstract fun provideRepository(repositoryImpl: AppRepositoryImpl): AppRepository

}