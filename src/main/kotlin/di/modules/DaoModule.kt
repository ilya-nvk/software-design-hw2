package com.ilyanvk.di.modules

import com.ilyanvk.data.dao.GenericDao
import com.ilyanvk.data.dao.GenericDaoImpl
import com.ilyanvk.data.localstorage.LocalStorageManager
import com.ilyanvk.domain.model.MenuItem
import com.ilyanvk.domain.model.Order
import com.ilyanvk.domain.model.User
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    @Provides
    fun provideUsersDao(localStorageManager: LocalStorageManager<List<User>>): GenericDao<User> =
        GenericDaoImpl(localStorageManager)

    @Provides
    fun provideMenuItemsDao(localStorageManager: LocalStorageManager<List<MenuItem>>): GenericDao<MenuItem> =
        GenericDaoImpl(localStorageManager)

    @Provides
    fun provideOrdersDao(localStorageManager: LocalStorageManager<List<Order>>): GenericDao<Order> =
        GenericDaoImpl(localStorageManager)
}