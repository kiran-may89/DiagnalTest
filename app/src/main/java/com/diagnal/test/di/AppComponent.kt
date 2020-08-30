package com.diagnal.test.di

import com.diagnal.test.App
import com.diagnal.test.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetModule::class, ActivityBuilderModule::class, FactoryModule::class])
interface AppComponent:AndroidInjector<App> {

    @Component.Builder
     interface Builder {
        @BindsInstance
        fun bindApplication(application: App): Builder
        fun build(): AppComponent
    }
}