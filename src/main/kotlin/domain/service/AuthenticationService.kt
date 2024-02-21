package com.ilyanvk.domain.service

import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.model.util.UserType

interface AuthenticationService {
    fun getCurrentUser(): User?
    fun authenticate(username: String, password: String): User
    fun register(username: String, password: String, userType: UserType): User
}
