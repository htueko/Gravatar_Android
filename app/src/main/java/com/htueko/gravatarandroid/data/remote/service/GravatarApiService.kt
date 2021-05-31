package com.htueko.gravatarandroid.data.remote.service

import com.htueko.gravatarandroid.data.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GravatarApiService {

    /**
     * @param name is the name of the person
     * @return eg https://en.gravatar.com/htueko.json
     * @return List of [ApiResponse]
     */
    @GET("{name}.json")
    suspend fun getPerson(
        @Path("name") name: String
    ): Response<ApiResponse>

}