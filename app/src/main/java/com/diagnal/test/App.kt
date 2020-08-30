package com.diagnal.test

import android.app.Activity
import android.app.Application
import com.diagnal.test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class App:DaggerApplication(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>;

    override fun onCreate() {
        super.onCreate()



    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .bindApplication(this)
            .build();
        return appComponent
    }

    override fun androidInjector(): DispatchingAndroidInjector<Any> {

        return dispatchingAndroidInjector;

    }
}