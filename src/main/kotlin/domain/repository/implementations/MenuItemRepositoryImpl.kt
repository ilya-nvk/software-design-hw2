package com.ilyanvk.domain.repository.implementations

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.repository.MenuItemRepository

class MenuItemRepositoryImpl(
    private val dao: GenericDao<MenuItem>,
) : MenuItemRepository {
    override fun getMenuItems(): List<MenuItem> = dao.getAll()

    override fun addMenuItem(item: MenuItem) {
        require(dao.getAll().none { it.name == item.name }) {
            "Menu item with name ${item.name} already exists"
        }
        dao.add(item)
    }

    override fun removeMenuItem(item: MenuItem) = dao.delete(item)

}