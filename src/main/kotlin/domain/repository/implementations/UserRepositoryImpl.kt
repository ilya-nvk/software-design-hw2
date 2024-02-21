package com.ilyanvk.domain.repository.implementations

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: GenericDao<User>,
) : UserRepository {
    override fun addUser(user: User) {
        val users = dao.getAll()
        require(users.none { it.username == user.username }) {
            "Username is already taken"
        }
        dao.add(user)
    }

    override fun getUser(username: String): User {
        val user = dao.getAll().find { it.username == username }
        requireNotNull(user) { "User not found" }
        return user
    }

    override fun deleteUser(user: User) = dao.delete(user)
}