package com.ilyanvk.di.modules

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.ilyanvk.data.adapters.OrderDeserializer
import com.ilyanvk.data.adapters.OrderSerializer
import com.ilyanvk.data.localstorage.LocalStorageManager
import com.ilyanvk.data.localstorage.LocalStorageManagerImpl
import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.User
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideUserStorage(): LocalStorageManager<List<User>> =
        LocalStorageManagerImpl("users.json", GsonBuilder().create(), object : TypeToken<List<User>>() {})

    @Provides
    fun provideMenuItemsStorage(): LocalStorageManager<List<MenuItem>> =
        LocalStorageManagerImpl("menu_items.json", GsonBuilder().create(), object : TypeToken<List<MenuItem>>() {})

    @Provides
    fun provideOrderStorage(): LocalStorageManager<List<Order>> = LocalStorageManagerImpl("orders.json",
        GsonBuilder().registerTypeAdapter(Order::class.java, OrderSerializer())
            .registerTypeAdapter(Order::class.java, OrderDeserializer()).create(),
        object : TypeToken<List<Order>>() {})
}
