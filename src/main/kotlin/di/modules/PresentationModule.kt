package com.ilyanvk.di.modules

import com.ilyanvk.domain.repository.OrderRepository
import com.ilyanvk.domain.service.AuthenticationService
import com.ilyanvk.domain.service.MenuService
import com.ilyanvk.domain.service.OrderService
import com.ilyanvk.presentation.AdminUI
import com.ilyanvk.presentation.CommonUI
import com.ilyanvk.presentation.InputReader
import com.ilyanvk.presentation.InputReaderImpl
import dagger.Module
import dagger.Provides
import presentation.ClientUI

@Module
class PresentationModule {
    @Provides
    fun provideInputReader(): InputReader = InputReaderImpl()

    @Provides
    fun provideClientUI(
        menuService: MenuService,
        orderService: OrderService,
        inputReader: InputReader,
    ): ClientUI = ClientUI(menuService, orderService, inputReader)

    @Provides
    fun provideAdminUI(
        menuItemService: MenuService,
        orderRepository: OrderRepository,
        inputReader: InputReader,
    ): AdminUI = AdminUI(menuItemService, orderRepository, inputReader)

    @Provides
    fun provideCommonUI(
        clientUI: ClientUI,
        adminUI: AdminUI,
        authenticationService: AuthenticationService,
        inputReader: InputReader,
    ): CommonUI = CommonUI(inputReader, clientUI, adminUI, authenticationService)
}

