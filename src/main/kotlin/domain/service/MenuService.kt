package com.ilyanvk.domain.service

import com.ilyanvk.domain.model.MenuItem

interface MenuService {
    fun getMenuItems(): List<MenuItem>
    fun addMenuItem(name: String, price: Double, preparationTime: Int)
    fun removeMenuItem(menuItem: MenuItem)
}