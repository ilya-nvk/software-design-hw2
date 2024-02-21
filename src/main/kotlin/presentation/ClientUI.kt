package presentation

import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.service.MenuService
import com.ilyanvk.domain.service.OrderBuilder
import com.ilyanvk.domain.service.OrderService
import com.ilyanvk.presentation.InputReader
import kotlin.system.exitProcess

class ClientUI (
    private val menuService: MenuService,
    private val orderService: OrderService,
    private val inputReader: InputReader
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
                1 -> makeOrder()
                2 -> seeMyCurrentOrders()
                3 -> cancelOrder()
                4 -> exit()
                else -> println("Invalid choice")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun cancelOrder() {
        val orders = orderService.getCookingOrders()
        if (orders.isEmpty()) {
            println("You have no current orders")
        } else {
            for ((index, order) in orders.withIndex()) {
                println("${index + 1}. $order")
            }
            print("Enter the number of the order you want to cancel: ")
            val orderNumber = inputReader.readChoice()
            if (orderNumber in 1..orders.size) {
                orderService.cancelOrder(orders[orderNumber - 1])
                println("Order cancelled")
            } else {
                println("Invalid order number")
            }
        }
    }

    private fun exit() {
        println("Goodbye. You orders are cancelled")
        exitProcess(0)
    }

    private fun seeMyCurrentOrders() {
        val orders = orderService.getCookingOrders()
        if (orders.isEmpty()) {
            println("You have no current orders")
        } else {
            for ((index, order) in orders.withIndex()) {
                println("${index + 1}. $order")
            }
        }
    }

    private fun makeOrder() {
        val orderBuilder = OrderBuilder()
        while (true) {
            println("1. Add item")
            println("2. Submit order")
            println("3. Cancel order")
            print("Enter your choice: ")
            when (inputReader.readChoice()) {
                1 -> addItem(orderBuilder)
                2 -> submitOrder(orderBuilder)
                3 -> return
                else -> println("Invalid choice")
            }
        }
    }

    private fun submitOrder(orderBuilder: OrderBuilder) {
        val order = orderBuilder.build()
        println("Order submitted. The total price is ${order.price} and the preparation time is ${order.preparationTimeMin} minutes.")
        orderService.placeOrder(order) {
            println("Your order is ready")
            payForOrder(order)
        }
    }

    private fun payForOrder(order: Order) {
        println("1. Pay for the order (total price: ${order.price})")
        print("Enter your choice: ")
        if (inputReader.readChoice() == 1) println("Payment successful")
        else println("Invalid choice")
    }

    private fun addItem(orderBuilder: OrderBuilder) {
        val menuItems = menuService.getMenuItems()
        for ((index, menuItem) in menuItems.withIndex()) {
            println("${index + 1}. $menuItem")
        }
        print("Enter the number of the item you want to add: ")
        val itemNumber = inputReader.readChoice()
        if (itemNumber in 1..menuItems.size) {
            orderBuilder.addItem(menuItems[itemNumber - 1])
            println("Item added")
        } else {
            println("Invalid item number")
        }
    }

    private fun printMenu() {
        println("1. Make order")
        println("2. See my current orders")
        println("3. Cancel order")
        println("4. Exit")
    }
}