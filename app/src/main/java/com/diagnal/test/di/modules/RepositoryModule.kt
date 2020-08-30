package com.diagnal.test.di.modules

import com.diagnal.test.di.scopes.MainScope
import com.diagnal.test.services.Services
import com.diagnal.test.repository.DataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @MainScope
    @Provides
    fun providesDataRepository(services: Services):DataRepository  = DataRepository(services)
}