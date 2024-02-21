package com.ilyanvk.domain.repository

import com.ilyanvk.domain.model.MenuItem

interface MenuItemRepository {
    fun getMenuItems(): List<MenuItem>
    fun addMenuItem(item: MenuItem)
    fun removeMenuItem(item: MenuItem)
}