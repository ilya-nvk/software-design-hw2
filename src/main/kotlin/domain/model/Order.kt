package com.ilyanvk.domain.model

import com.ilyanvk.domain.model.util.OrderStatus
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter

data class Order(
    val id: Int,
    val username: String,
    val time: Long,
    val items: Map<MenuItem, Int>,
    val status: OrderStatus = OrderStatus.PLACED,
) {
    @Transient
    val preparationTimeMin = items.entries.sumOf { it.key.preparationTimeMin * it.value }
    @Transient
    val price = items.entries.sumOf { it.key.price * it.value }

    override fun toString(): String {
        val timeFormatted = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), systemDefault())
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val builder = StringBuilder()
        builder.append("Username = '$username', time = $timeFormatted, status = ${status.name}, ")
        builder.append("items = {")
        items.entries.joinTo(builder, ", ") { (menuItem, quantity) ->
            "${menuItem.name} x $quantity"
        }
        builder.append("}, ")
        builder.append("preparationTimeMin = $preparationTimeMin min, ")
        builder.append("price = $price roubles")
        return builder.toString()
    }

}