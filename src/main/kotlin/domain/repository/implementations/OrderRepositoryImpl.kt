package com.ilyanvk.domain.repository.implementations

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val dao: GenericDao<Order>
) : OrderRepository {
    override fun placeOrder(order: Order) = dao.add(order)

    override fun getAllOrders(): List<Order> = dao.getAll()
}