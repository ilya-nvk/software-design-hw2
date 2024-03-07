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
    private val orderJobs = mutableMapOf<Int, Job>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        val orders = orderRepository.getAllOrders()
        orders.filter { it.status == OrderStatus.COOKING }.forEach {
            orderJobs[it.id] = coroutineScope.launch {
                delay(it.preparationTimeMin * 1000L - (System.currentTimeMillis() - it.time)) // 1 min = 1 second
                orderRepository.updateOrder(it.copy(status = OrderStatus.COOKED))
                println("Order for client '${it.username}' is ready!")
            }
        }
    }

    override fun getCookingOrders(): List<Order> =
        orderRepository.getAllOrders().filter { it.status == OrderStatus.COOKING }.toList()

    override fun getCookingOrdersByUser(username: String): List<Order> =
        orderRepository.getAllOrders().filter { it.status == OrderStatus.COOKING && it.username == username }.toList()

    override fun getOrdersToPay(username: String): List<Order> =
        orderRepository.getAllOrders().filter { it.status == OrderStatus.COOKED && it.username == username }

    override fun getAllOrders(): List<Order> = orderRepository.getAllOrders()

    override fun placeOrder(order: Order) {
        val newOrder = order.copy(id = (orderRepository.getAllOrders().maxByOrNull { it.id }?.id ?: 0) + 1)
        orderJobs[newOrder.id] = coroutineScope.launch {
            orderRepository.placeOrder(newOrder.copy(status = OrderStatus.PLACED))
            orderRepository.updateOrder(newOrder.copy(status = OrderStatus.COOKING))
            delay(newOrder.preparationTimeMin * 1000L) // 1 min = 1 second
            orderRepository.updateOrder(newOrder.copy(status = OrderStatus.COOKED))
            println("Order for client '${newOrder.username}' is ready!")
        }
    }

    override fun payForOrder(order: Order) {
        orderRepository.updateOrder(order.copy(status = OrderStatus.PAYED))
    }

    override fun cancelOrder(order: Order) {
        orderJobs[order.id]?.cancel()
        orderJobs.remove(order.id)
        orderRepository.updateOrder(order.copy(status = OrderStatus.CANCELLED))
    }
}