package com.ilyanvk.presentation

import com.ilyanvk.domain.model.util.UserType
import com.ilyanvk.domain.service.AuthenticationService
import presentation.ClientUI
import kotlin.system.exitProcess

class CommonUI(
    private val inputReader: InputReader,
    private val clientUI: ClientUI,
    private val adminUI: AdminUI,
    private val authenticationService: AuthenticationService,
) {
    fun start() {
        while (true) {
            while (authenticationService.getCurrentUser() == null) {
                authenticate()
            }
            val user = authenticationService.getCurrentUser()
            if (user?.userType == UserType.CLIENT) {
                clientUI.start(user)
            } else {
                adminUI.start()
            }
            authenticationService.logout()
        }
    }

    private fun authenticate() {
        println("1. Login")
        println("2. Register")
        println("3. Exit")
        print("Enter your choice: ")
        val choice = inputReader.readChoice()
        try {
            when (choice) {
                1 -> login()
                2 -> register()
                3 -> exit()
                else -> println("Invalid choice")
            }
            return
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun login() {
        print("Enter your username: ")
        val username = readln()
        print("Enter your password: ")
        val password = readln()
        authenticationService.authenticate(username, password)
    }

    private fun register() {
        println("Enter your account type:")
        println("1. Client")
        println("2. Admin")
        print("Enter your choice: ")
        val choice = inputReader.readChoice()
        val userType = when (choice) {
            1 -> UserType.CLIENT
            2 -> UserType.ADMINISTRATOR
            else -> {
                throw Exception("Invalid choice")
            }
        }

        print("Enter your username: ")
        val username = readln()
        print("Enter your password: ")
        val password = readln()

        authenticationService.register(username, password, userType)
    }

    private fun exit() {
        println("Exiting the program!")
        exitProcess(0)
    }
}