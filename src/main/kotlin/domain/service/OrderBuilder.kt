package com.ilyanvk.domain.service

import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.util.OrderStatus

class OrderBuilder {
    private var id = 0
    private var username = ""
    private var time = System.currentTimeMillis()
    private val items = mutableMapOf<MenuItem, Int>()
    private var status = OrderStatus.PLACED

    fun setId(id: Int): OrderBuilder {
        this.id = id
        return this
    }

    fun setUsername(username: String): OrderBuilder {
        this.username = username
        return this
    }

    fun setTime(time: Long): OrderBuilder {
        this.time = time
        return this
    }

    fun addItem(item: MenuItem): OrderBuilder {
        items[item] = (items[item] ?: 0) + 1
        return this
    }

    fun setStatus(status: OrderStatus): OrderBuilder {
        this.status = status
        return this
    }

    fun build(): Order {
        return Order(id, username, time, items, status)
    }
}