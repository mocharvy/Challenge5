package com.programmer.challenge5_ma.item

data class MenuItem(
    val name: String,
    val price: Int,
    val description: String,
    val imageRes: Int,
    val restaurantAddress: String,
    val googleMapsUrl: String = ""
)