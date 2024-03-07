package com.ilyanvk.domain.service.implementation

import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.repository.MenuItemRepository
import com.ilyanvk.domain.service.MenuService

class MenuServiceImpl(
    private val repository: MenuItemRepository,
) : MenuService {
    override fun getMenuItems(): List<MenuItem> = repository.getMenuItems()

    override fun addMenuItem(name: String, price: Double, preparationTime: Int) {
        val menuItem = MenuItem(name, price, preparationTime)
        repository.addMenuItem(menuItem)
    }

    override fun removeMenuItem(menuItem: MenuItem) = repository.removeMenuItem(menuItem)
}