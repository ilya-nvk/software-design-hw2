package com.ilyanvk.data.adapters

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.ilyanvk.domain.model.Order
import java.lang.reflect.Type

class OrderSerializer : JsonSerializer<Order> {
    override fun serialize(order: Order?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        if (order == null) return jsonObject
        jsonObject.add("id", context?.serialize(order.id))
        jsonObject.add("username", context?.serialize(order.username))
        jsonObject.add("time", context?.serialize(order.time))
        jsonObject.add("status", context?.serialize(order.status.ordinal))
        jsonObject.addProperty("items", order.items.entries.joinToString(",") { (menuItem, quantity) ->
            "${menuItem.name};${menuItem.price};${menuItem.preparationTimeMin};$quantity"
        })
        return jsonObject
    }
}