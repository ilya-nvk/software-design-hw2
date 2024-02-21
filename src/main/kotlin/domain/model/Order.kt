package com.ilyanvk.domain.model

import com.ilyanvk.domain.model.util.OrderStatus

data class Order(
    val items: Map<MenuItem, Int>,
    val status: OrderStatus = OrderStatus.PLACED,
) {
    val preparationTimeMin = items.entries.sumOf { it.key.preparationTimeMin * it.value }
    val price = items.entries.sumOf { it.key.price * it.value }
}