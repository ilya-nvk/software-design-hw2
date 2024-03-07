package com.ilyanvk.di.modules

import com.ilyanvk.domain.repository.MenuItemRepository
import com.ilyanvk.domain.repository.OrderRepository
import com.ilyanvk.domain.repository.UserRepository
import com.ilyanvk.domain.service.AuthenticationService
import com.ilyanvk.domain.service.MenuService
import com.ilyanvk.domain.service.OrderService
import com.ilyanvk.domain.service.implementation.AuthenticationServiceImpl
import com.ilyanvk.domain.service.implementation.MenuServiceImpl
import com.ilyanvk.domain.service.implementation.OrderServiceImpl
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideAuthenticationService(usersRepository: UserRepository): AuthenticationService =
        AuthenticationServiceImpl(usersRepository)

    @Provides
    fun provideMenuService(menuRepository: MenuItemRepository): MenuService =
        MenuServiceImpl(menuRepository)

    @Provides
    fun provideOrderService(orderRepository: OrderRepository): OrderService =
        OrderServiceImpl(orderRepository)
}