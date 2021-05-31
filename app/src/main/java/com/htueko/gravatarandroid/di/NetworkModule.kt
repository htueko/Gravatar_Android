package com.htueko.gravatarandroid.di

import android.content.Context
import com.htueko.gravatarandroid.data.remote.RemoteConstant
import com.htueko.gravatarandroid.data.remote.service.GravatarApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    // to provide api base url
    @Provides
    @Singleton
    fun provideBaseUrl() = RemoteConstant.BASE_URL

    // to provide retrofit instance
    @Provides
    @Singleton
    fun provideRetrofitInstance(
        @ApplicationContext app: Context
    ): Retrofit = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // to provide api service
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GravatarApiService = retrofit.create(
        GravatarApiService::class.java
    )

}