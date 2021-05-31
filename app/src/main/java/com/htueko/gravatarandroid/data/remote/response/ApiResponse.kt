package com.htueko.gravatarandroid.data.remote.response


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ApiResponse(
    @SerializedName("entry")
    val entry: List<Entry>
) : Parcelable