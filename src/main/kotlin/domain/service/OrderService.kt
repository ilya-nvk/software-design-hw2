package com.ilyanvk.domain.service

import com.ilyanvk.domain.model.Order

interface OrderService {
    fun getCookingOrders(): List<Order>
    fun placeOrder(order: Order, onCooked: () -> Unit)
    fun cancelOrder(order: Order)
}