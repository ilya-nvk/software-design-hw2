package com.ilyanvk.data.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.util.OrderStatus
import java.lang.reflect.Type

class OrderDeserializer : JsonDeserializer<Order> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Order {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asInt ?: 0
        val username = jsonObject?.get("username")?.asString ?: ""
        val time = jsonObject?.get("time")?.asLong ?: System.currentTimeMillis()
        val status = OrderStatus.entries[jsonObject?.get("status")?.asInt ?: 0]
        val items = mutableMapOf<MenuItem, Int>()
        jsonObject?.get("items")?.asString?.split(",")?.forEach { entry ->
            val (name, price, preparationTimeMin, quantity) = entry.split(";")
            items[MenuItem(name = name, price = price.toDouble(), preparationTimeMin = preparationTimeMin.toInt())] =
                quantity.toInt()
        }
        return Order(id, username, time, items, status)
    }
}