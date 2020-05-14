package com.example.tachelist_retrofit


import com.google.gson.annotations.SerializedName

data class ToDo(
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)