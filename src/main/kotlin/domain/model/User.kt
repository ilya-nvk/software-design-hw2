package com.ilyanvk.domain.model

import com.ilyanvk.domain.model.util.UserType

data class User(val username: String, val passwordHash: Int, val userType: UserType)