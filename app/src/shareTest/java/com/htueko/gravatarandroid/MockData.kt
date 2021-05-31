package com.htueko.gravatarandroid

import com.htueko.gravatarandroid.data.local.entity.Person

object MockData {

    private const val id = 1L
    private const val anotherId = 2L
    private const val hash = "hash"
    private const val imageUrl = "imageUrl"
    private const val name = "John Doe"
    private const val updateName = "Update John Doe"
    private const val email = "johndoe@abc.com"
    private const val isOnline = true

    val samplePerson = Person(
        id,
        hash,
        imageUrl,
        name,
        email,
        isOnline
    )

    val updatePerson = Person(
        id,
        hash,
        imageUrl,
        updateName,
        email,
        isOnline
    )

    val anotherPerson = Person(
        anotherId,
        hash,
        imageUrl,
        updateName,
        email,
        isOnline
    )

}