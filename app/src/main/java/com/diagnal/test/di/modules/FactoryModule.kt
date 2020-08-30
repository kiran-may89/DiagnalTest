package com.diagnal.test.di.modules

import androidx.lifecycle.ViewModelProvider
import com.diagnal.test.di.modules.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class FactoryModule {
    @Binds
    abstract fun bindFactory(factory: ViewModelProviderFactory):ViewModelProvider.Factory


}