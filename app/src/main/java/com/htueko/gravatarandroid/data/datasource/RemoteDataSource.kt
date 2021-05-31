package com.htueko.gravatarandroid.data.datasource

import com.htueko.gravatarandroid.data.remote.response.ApiResponse
import retrofit2.Response
import javax.inject.Singleton

/**
 * contract to implement for remote service
 */
@Singleton
interface RemoteDataSource {

    suspend fun getPerson(name: String): Response<ApiResponse>

}