package com.ilyanvk.di

import com.ilyanvk.Main
import com.ilyanvk.di.modules.DaoModule
import com.ilyanvk.di.modules.PresentationModule
import com.ilyanvk.di.modules.RepositoryModule
import com.ilyanvk.di.modules.ServiceModule
import com.ilyanvk.di.modules.StorageModule
import dagger.Component

@Component(
    modules = [
        DaoModule::class,
        PresentationModule::class,
        RepositoryModule::class,
        ServiceModule::class,
        StorageModule::class
    ]
)
interface AppComponent {
    fun inject(main: Main)
}