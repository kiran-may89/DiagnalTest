package com.diagnal.test.services.mocks

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class MockCall<T>:Call<T> {
    override fun enqueue(callback: Callback<T>) {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun clone(): Call<T> {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<T> {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }
    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}