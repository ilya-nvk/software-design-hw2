package com.ilyanvk.domain.service

import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.util.OrderStatus

class OrderBuilder {
    private val _items = mutableMapOf<MenuItem, Int>()
    private var status = OrderStatus.PLACED
    val items: Map<MenuItem, Int>
        get() = _items.toMap()

    fun addItem(item: MenuItem): OrderBuilder {
        _items[item] = (_items[item] ?: 0) + 1
        return this
    }

    fun setStatus(status: OrderStatus): OrderBuilder {
        this.status = status
        return this
    }

    fun build(): Order {
        return Order(_items, status)
    }
}