package com.htueko.gravatarandroid.data.remote.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entry(
    @SerializedName("id")
    val id: String,
    @SerializedName("hash")
    val hash: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("profileUrl")
    val profileUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
) : Parcelable