package com.htueko.gravatarandroid.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.htueko.gravatarandroid.data.local.dao.PersonDao
import com.htueko.gravatarandroid.data.local.LocalConstant
import com.htueko.gravatarandroid.data.local.entity.Person

@Database(entities = [Person::class], version = LocalConstant.DB_VERSION, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
}