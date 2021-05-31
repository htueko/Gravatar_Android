package com.htueko.gravatarandroid.data.datasource

import com.htueko.gravatarandroid.data.local.entity.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

/**
 * contract to implement for local database operation
 */
@Singleton
interface LocalDataSource {

    suspend fun addPerson(person: Person)

    suspend fun updatePerson(person: Person)

    suspend fun deletePerson(person: Person)

    fun getAllPerson(): Flow<List<Person>>

    fun getPersonById(id: Long): Flow<List<Person>>

}