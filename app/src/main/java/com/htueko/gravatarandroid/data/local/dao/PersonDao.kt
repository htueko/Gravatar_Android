package com.htueko.gravatarandroid.data.local.dao

import androidx.room.*
import com.htueko.gravatarandroid.data.local.entity.Person
import kotlinx.coroutines.flow.Flow

/**
 * Data access object to Person database
 */
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addPerson(person: Person)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("select * from person_table")
    fun getAllPerson(): Flow<List<Person>>

    @Query("select * from person_table where id = :id")
    fun getPersonById(id: Long): Flow<List<Person>>

}