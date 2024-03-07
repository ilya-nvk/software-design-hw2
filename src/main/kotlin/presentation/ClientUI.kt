package presentation

import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.service.MenuService
import com.ilyanvk.domain.service.OrderBuilder
import com.ilyanvk.domain.service.OrderService
import com.ilyanvk.presentation.ExitProfileException
import com.ilyanvk.presentation.InputReader

class ClientUI(
    private val menuService: MenuService,
    private val orderService: OrderService,
    private val inputReader: InputReader,
) {
    private lateinit var client: User
    fun start(client: User) {
        this.client = client
        while (true) {
            printMenu()
            try {
                handleChoice(inputReader.readChoice())
            } catch (e: ExitProfileException) {
                return
            }
        }
    }

    private fun handleChoice(choice: Int) {
        try {
            when (choice) {
                1 -> makeOrder()
                2 -> seeMyCurrentOrders()
                3 -> cancelOrder()
                4 -> payForOrders()
                5 -> exit()
                else -> println("Invalid choice")
            }
        } catch (e: ExitProfileException) {
            throw e
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun payForOrders() {
        val orders = orderService.getOrdersToPay(client.username)
        if (orders.isEmpty()) {
            println("You have no orders to pay")
        } else {
            for ((index, order) in orders.withIndex()) {
                println("${index + 1}. $order")
            }
            println("Total: ${orders.sumOf { it.price }}")
            println("Type anything to pay: ")
            readln()
            orders.forEach { orderService.payForOrder(it) }
            println("You payed successfully!")
        }
    }

    private fun cancelOrder() {
        val orders = orderService.getCookingOrders()
        if (orders.isEmpty()) {
            println("You have no cooking orders")
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
        println("Goodbye.")
        throw ExitProfileException()
    }

    private fun seeMyCurrentOrders() {
        val orders = orderService.getCookingOrdersByUser(client.username)
        if (orders.isEmpty()) {
            println("You have no cooking orders")
        } else {
            for ((index, order) in orders.withIndex()) {
                println("${index + 1}. $order")
            }
        }
    }

    private fun makeOrder() {
        val orderBuilder = OrderBuilder().setUsername(client.username).setTime(System.currentTimeMillis())
        while (true) {
            println("1. Add item")
            println("2. Submit order")
            println("3. Cancel order")
            print("Enter your choice: ")
            when (inputReader.readChoice()) {
                1 -> addItem(orderBuilder)
                2 -> {
                    submitOrder(orderBuilder)
                    return
                }

                3 -> return
                else -> println("Invalid choice")
            }
        }
    }

    private fun submitOrder(orderBuilder: OrderBuilder) {
        val order = orderBuilder.build()
        println("Order submitted. The total price is ${order.price} and the preparation time is ${order.preparationTimeMin} minutes.")
        orderService.placeOrder(order)
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
        println("4. Pay for orders")
        println("5. Exit")
    }
}