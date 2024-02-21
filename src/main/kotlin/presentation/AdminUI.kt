package com.ilyanvk.presentation

import com.ilyanvk.domain.repository.OrderRepository
import com.ilyanvk.domain.service.MenuService
import kotlin.system.exitProcess

class AdminUI(
    private val menuItemService: MenuService,
    private val orderRepository: OrderRepository,
    private val inputReader: InputReader,
) {
    fun start() {
        while (true) {
            printMenu()
            handleChoice(inputReader.readChoice())
        }
    }

    private fun handleChoice(choice: Int) {
        try {
            when (choice) {
                1 -> addMenuItem()
                2 -> removeMenuItem()
                3 -> viewOrders()
                4 -> viewMenu()
                5 -> exit()
                else -> println("Invalid choice")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun viewMenu() {
        val menuItems = menuItemService.getMenuItems()
        if (menuItems.isEmpty()) {
            println("No menu items")
        } else {
            for ((index, menuItem) in menuItems.withIndex()) {
                println("${index + 1}. $menuItem")
            }
        }
    }

    private fun addMenuItem() {
        print("Enter the name of the menu item: ")
        val name = inputReader.readString()
        print("Enter the price of the menu item: ")
        val price = readln().toDouble()
        if (price < 0) {
            println("Price cannot be negative")
            return
        }
        println("Enter cooking time in minutes: ")
        val cookingTime = readln().toInt()
        if (cookingTime < 0) {
            println("Cooking time cannot be negative")
            return
        }
        menuItemService.addMenuItem(name, price, cookingTime)
        println("Menu item added")
    }

    private fun removeMenuItem() {
        val menuItems = menuItemService.getMenuItems()
        if (menuItems.isEmpty()) {
            println("No menu items")
        } else {
            for ((index, menuItem) in menuItems.withIndex()) {
                println("${index + 1}. $menuItem")
            }
            print("Enter the number of the menu item you want to remove: ")
            val menuItemNumber = inputReader.readChoice()
            if (menuItemNumber in 1..menuItems.size) {
                menuItemService.removeMenuItem(menuItems[menuItemNumber - 1])
                println("Menu item removed")
            } else {
                println("Invalid menu item number")
            }
        }
    }

    private fun viewOrders() {
        val orders = orderRepository.getAllOrders()
        if (orders.isEmpty()) {
            println("No orders")
        } else {
            for ((index, order) in orders.withIndex()) {
                println("${index + 1}. $order")
            }
            println("Total orders: ${orders.size}")
            println("Total revenue: ${orders.sumOf { it.price }}")
        }
    }

    private fun exit() {
        println("Goodbye")
        exitProcess(0)
    }

    private fun printMenu() {
        println("1. Add menu item")
        println("2. Remove menu item")
        println("3. View orders")
        println("4. View menu")
        println("5. Exit")
    }
}