package com.programmer.challenge5_ma.menu

import com.google.gson.annotations.SerializedName

data class MenuList(
    @SerializedName("data")
    val data: List<MenuListData>,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)