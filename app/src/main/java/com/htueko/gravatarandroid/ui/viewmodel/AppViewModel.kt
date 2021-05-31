package com.htueko.gravatarandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.data.mockdata.listOfUser
import com.htueko.gravatarandroid.repository.AppRepository
import com.htueko.mayremo.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel() {

    private val _peopleLiveData = MutableLiveData<State<List<Person>>>()
    val peopleLiveData: LiveData<State<List<Person>>>
        get() = _peopleLiveData

    fun getPeople() = viewModelScope.launch {
        val mutex = Mutex()
        var counter = 0
        withContext(Dispatchers.IO) {
            repeat(counter + 1) {
                launch {
                    listOfUser.forEach { name ->
                        mutex.withLock {
                            repo.getPersons(name).collect {
                                _peopleLiveData.postValue(it)
                            }
                            counter++
                        }
                    }
                }
            }
        }
    }

    fun shuffleData(data: List<Person>): List<Person> = multiplyData(data).shuffled()

    fun multiplyData(data: List<Person>): List<Person> {
        val firstSet = data.shuffled()
        val secondSet = data.shuffled()
        val thirdSet = data.shuffled()
        val fourthSet = data.shuffled()
        return firstSet + secondSet + thirdSet + fourthSet
    }

}