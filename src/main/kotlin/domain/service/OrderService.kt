package com.ilyanvk.domain.service

import com.ilyanvk.domain.model.Order

interface OrderService {
    fun getCookingOrders(): List<Order>
    fun getCookingOrdersByUser(username: String): List<Order>
    fun getOrdersToPay(username: String): List<Order>
    fun getAllOrders(): List<Order>
    fun placeOrder(order: Order)
    fun payForOrder(order: Order)
    fun cancelOrder(order: Order)
}