package com.htueko.gravatarandroid.repository

import com.htueko.gravatarandroid.data.datasource.LocalDataSource
import com.htueko.gravatarandroid.data.datasource.RemoteDataSource
import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.data.remote.response.ApiResponse
import com.htueko.gravatarandroid.data.remote.response.Entry
import com.htueko.gravatarandroid.util.ModelWrapper
import com.htueko.gravatarandroid.util.NetworkBoundResource
import com.htueko.mayremo.util.State
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
class AppRepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : AppRepository {

    override suspend fun getRemotePersons(name: String): Response<ApiResponse> =
        remote.getPerson(name)

    override suspend fun getLocalPersons() = local.getAllPerson()

    override suspend fun saveRemotePersons(people: List<Entry>) {
        people.forEach { entry ->
            local.addPerson(ModelWrapper.toEntityModel(entry))
        }
    }

    override fun getPersons(name: String): Flow<State<List<Person>>> {
        return object : NetworkBoundResource<List<Person>, ApiResponse>() {
            override suspend fun saveRemoteData(item: ApiResponse) =
                saveRemotePersons(item.entry)

            override suspend fun fetchFromLocal(): Flow<List<Person>> =
                getLocalPersons()

            override suspend fun fetchFromRemote(name: String): Response<ApiResponse> =
                getRemotePersons(name)

        }.asFlow(name)
    }

}