package com.ilyanvk.domain.repository

import com.ilyanvk.domain.model.Order

interface OrderRepository {
    fun placeOrder(order: Order)
    fun getAllOrders(): List<Order>
}