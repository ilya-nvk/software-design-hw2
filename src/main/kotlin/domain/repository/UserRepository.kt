package com.ilyanvk.domain.repository

import com.ilyanvk.domain.model.User

interface UserRepository {
    fun addUser(user: User)
    fun getUser(username: String): User
    fun deleteUser(user: User)
}

