package com.htueko.gravatarandroid.util

import com.htueko.gravatarandroid.data.local.entity.Person
import com.htueko.gravatarandroid.data.remote.response.Entry


/**
 * helper class to convert entity model and response model
 */
object ModelWrapper {

    //to entity model
    fun toEntityModel(response: Entry): Person {
        val randomNumber = (1..2).random()
        return Person(
            id = response.id.toLong(),
            hash = response.hash,
            name = response.displayName,
            imageUrl = response.thumbnailUrl,
            // if random number is equal to 1 set true, otherwise set false
            isOnline = randomNumber == 1,
            email = response.displayName
        )
    }

}