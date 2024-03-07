package com.ilyanvk.domain.service.implementation

import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.model.util.UserType
import com.ilyanvk.domain.repository.UserRepository
import com.ilyanvk.domain.service.AuthenticationService

class AuthenticationServiceImpl(
    private val repository: UserRepository,
) : AuthenticationService {
    private var currentUser: User? = null
    override fun getCurrentUser(): User? = currentUser

    override fun authenticate(username: String, password: String): User {
        val user = repository.getUser(username)
        require(user.passwordHash == password.hashCode()) { "Invalid password" }
        currentUser = user
        return user
    }

    override fun register(username: String, password: String, userType: UserType): User {
        val user = User(username, password.hashCode(), userType)
        repository.addUser(user)
        currentUser = user
        return user
    }

    override fun logout() {
        currentUser = null
    }
}