package com.htueko.gravatarandroid.repository

import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.data.remote.response.ApiResponse
import com.htueko.gravatarandroid.data.remote.response.Entry
import com.htueko.mayremo.util.State
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton

@Singleton
interface AppRepository {

    suspend fun getRemotePersons(name: String): Response<ApiResponse>
    suspend fun getLocalPersons(): Flow<List<Person>>
    suspend fun saveRemotePersons(people: List<Entry>)
    fun getPersons(name: String): Flow<State<List<Person>>>

}