package com.diagnal.test.di.modules

import com.diagnal.test.App
import com.diagnal.test.services.Services
import com.diagnal.test.services.mocks.MockHelper
import com.diagnal.test.services.mocks.MockImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NetModule{
    // uncomment and add logging inteceptor and okhttp dependecies
    // If you want to call Apiservices
    /*
    @Singleton
    @Provides
    fun provideOkHttpClient(app:App): Cache {
val cacheSize = 10*1024*1024L
        return  Cache(app.cacheDir,cacheSize)

         @Singleton
    @Provides
    fun providesOkHttpClient(cache: Cache,logging:HttpLoggingInterceptor):OkHttpClient{

        val builder = OkHttpClient.Builder()
        builder.cache(cache).connectTimeout(10,TimeUnit.SECONDS).readTimeout(10,TimeUnit.SECONDS).interceptors().add(logging)
        return  builder.build()
    }
    @Singleton
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val logging= HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
    @Singleton
    @Provides
    fun providesRetrofit(gson:Gson, client: OkHttpClient):Retrofit{
        val retrofit = Retrofit.Builder()
        retrofit.addConverterFactory(GsonConverterFactory.create(gson))
        retrofit.baseUrl("https://testurl.com/")
        retrofit.client(client)
        return retrofit.build()

    }
    }*/


    @Singleton
    @Provides
    fun providesGson():Gson{
        val builder = GsonBuilder()
        return builder.create()
    }

    @Singleton
    @Provides
    fun providesMockImpl(mockHelper: MockHelper, gson: Gson,app: App):Services{
        return MockImpl(mockHelper, gson,app.assets)
    }
    @Singleton
    @Provides
    fun mockHelper():MockHelper = MockHelper()

}