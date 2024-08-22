package com.example.coin.login.extra.extra.restaurant.menu.domain

import java.io.Serializable

data class Order(
        val name: String,
        val quantity: Int,
        val price: Double,
        val total: Double
    ): Serializable