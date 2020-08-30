package com.diagnal.test.di.modules

import androidx.lifecycle.ViewModel
import com.diagnal.test.di.scopes.MainScope
import com.diagnal.test.di.scopes.ViewModelScope
import com.diagnal.test.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @MainScope
    @Binds
    @IntoMap
    @ViewModelScope(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel:MainViewModel): ViewModel
}