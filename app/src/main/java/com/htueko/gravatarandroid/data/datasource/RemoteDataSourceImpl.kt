package com.htueko.gravatarandroid.data.datasource

import com.htueko.gravatarandroid.data.remote.response.ApiResponse
import com.htueko.gravatarandroid.data.remote.service.GravatarApiService
import retrofit2.Response
import javax.inject.Inject

/**
 * Single source of remote data for the app
 */
class RemoteDataSourceImpl @Inject constructor(
    private val apiService: GravatarApiService
) : RemoteDataSource {

    override suspend fun getPerson(name: String): Response<ApiResponse> =
        apiService.getPerson(name)

}