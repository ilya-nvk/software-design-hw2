package com.ilyanvk.domain.service.implementation

import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.util.OrderStatus
import com.ilyanvk.domain.repository.OrderRepository
import com.ilyanvk.domain.service.OrderService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    private val cookingOrders = mutableMapOf<Order, Job>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getCookingOrders(): List<Order> = cookingOrders.keys.toList()

    override fun placeOrder(order: Order, onCooked: () -> Unit) {
        val newOrder = order.copy(status = OrderStatus.COOKING)
        cookingOrders[newOrder] = coroutineScope.launch {
            delay(order.preparationTimeMin * 60 * 1000L) // 1 min = 1 second
            onCooked()
            cookingOrders.remove(newOrder)
            orderRepository.placeOrder(newOrder.copy(status = OrderStatus.READY))
        }
    }

    override fun cancelOrder(order: Order) {
        cookingOrders[order]?.cancel() ?: {
            throw IllegalArgumentException("Order not found")
        }
        cookingOrders.remove(order)
    }
}