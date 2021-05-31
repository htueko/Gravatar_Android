package com.htueko.gravatarandroid.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.htueko.gravatarandroid.MainCoroutineRule
import com.htueko.gravatarandroid.MockData
import com.htueko.gravatarandroid.data.local.dao.PersonDao
import com.htueko.gravatarandroid.data.local.database.PersonDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class LocalDataSourceImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: PersonDatabase
    private lateinit var personDao: PersonDao

    @Before
    fun setUp() {
        hiltRule.inject()
        personDao = database.getPersonDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    /**
     * Mock Data from shareTest Directory
     */
    private val samplePerson = MockData.samplePerson
    private val anotherPerson = MockData.anotherPerson
    private val updatePerson = MockData.updatePerson

    /**
     * add [samplePerson] to [personDao]
     * get the data from [personDao]
     * assert the result
     */
    @Test
    fun test_add_person_to_db_return_person() = runBlockingTest {
        personDao.addPerson(samplePerson)
        val data = personDao.getAllPerson().first()
        assertThat(data[0]).isEqualTo(samplePerson)
    }

    /**
     * add [samplePerson] to [personDao]
     * add [updatePerson] to [personDao]
     * get the data from [personDao]
     * assert the result
     */
    @Test
    fun test_update_person_to_db_return_updated_person() = runBlockingTest {
        personDao.addPerson(samplePerson)
        personDao.updatePerson(updatePerson)
        val data = personDao.getAllPerson().first()
        assertThat(data[0]).isEqualTo(updatePerson)
    }

    /**
     * add [samplePerson] to [personDao]
     * assert the result
     * delete [samplePerson] from [personDao]
     * get the data from [personDao]
     * assert the result
     */
    @Test
    fun test_add_person_to_db_then_delete_return_nothing() = runBlockingTest {
        personDao.addPerson(samplePerson)
        val before = personDao.getAllPerson().first()
        assertThat(before[0]).isEqualTo(samplePerson)
        personDao.deletePerson(samplePerson)
        val after = personDao.getAllPerson().first()
        assertThat(after).isEmpty()
    }

    /**
     * add [samplePerson] to [personDao]
     * add [updatePerson] to [personDao]
     * get the data from [personDao]
     * assert the result
     */
    @Test
    fun test_add_person_to_db_when_called_by_id_return_person() = runBlockingTest {
        personDao.addPerson(samplePerson)
        val data = personDao.getPersonById(samplePerson.id).first()
        assertThat(data[0].id).isEqualTo(samplePerson.id)
    }

}