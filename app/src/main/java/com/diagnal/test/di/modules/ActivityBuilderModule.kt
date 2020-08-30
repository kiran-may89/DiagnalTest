package com.diagnal.test.di.modules

import com.diagnal.test.views.MainActivity
import com.diagnal.test.di.scopes.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class,ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity
}