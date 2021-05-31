package com.htueko.gravatarandroid.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.htueko.gravatarandroid.data.local.LocalConstant
import kotlinx.parcelize.Parcelize

/**
 * Person Data class for Database entity and Parcelize.
 */
@Entity(tableName = LocalConstant.tablePerson)
@Parcelize
data class Person(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val hash: String,
    val imageUrl: String,
    val name: String,
    val email: String,
    val isOnline: Boolean = false
) : Parcelable