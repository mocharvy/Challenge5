package com.programmer.challenge5_ma.menu

data class OrderRequest(
    val orders: List<Order>,
    val total: Int?,
    val username: String?
)