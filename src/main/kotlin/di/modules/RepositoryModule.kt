package com.ilyanvk.di.modules

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.User
import com.ilyanvk.domain.repository.MenuItemRepository
import com.ilyanvk.domain.repository.OrderRepository
import com.ilyanvk.domain.repository.UserRepository
import com.ilyanvk.domain.repository.implementations.MenuItemRepositoryImpl
import com.ilyanvk.domain.repository.implementations.OrderRepositoryImpl
import com.ilyanvk.domain.repository.implementations.UserRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(dao: GenericDao<User>): UserRepository {
        return UserRepositoryImpl(dao)
    }

    @Provides
    fun provideMenuItemRepository(dao: GenericDao<MenuItem>): MenuItemRepository {
        return MenuItemRepositoryImpl(dao)
    }

    @Provides
    fun provideOrderRepository(dao: GenericDao<Order>): OrderRepository {
        return OrderRepositoryImpl(dao)
    }

}