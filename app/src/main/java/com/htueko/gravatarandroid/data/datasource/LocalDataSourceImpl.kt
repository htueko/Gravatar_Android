package com.htueko.gravatarandroid.data.datasource

import com.htueko.gravatarandroid.data.local.dao.PersonDao
import com.htueko.gravatarandroid.data.local.entity.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Single source of local database data for the app
 */
class LocalDataSourceImpl @Inject constructor(
    private val personDao: PersonDao
) : LocalDataSource {
    override suspend fun addPerson(person: Person) = personDao.addPerson(person)

    override suspend fun updatePerson(person: Person) = personDao.updatePerson(person)

    override suspend fun deletePerson(person: Person) = personDao.deletePerson(person)

    override fun getAllPerson(): Flow<List<Person>> = personDao.getAllPerson()

    override fun getPersonById(id: Long): Flow<List<Person>> = personDao.getPersonById(id)
}