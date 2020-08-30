package com.diagnal.test.services.mocks

import android.content.res.AssetManager
import com.diagnal.test.services.Services
import com.diagnal.test.models.Movies
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MockImpl @Inject constructor(val helper: MockHelper, val gson: Gson, val asset:AssetManager) : Services {
    override fun fetchData(page: Int, folder:String): Call<Movies> {
        return object : MockCall<Movies>() {
            override fun enqueue(callback: Callback<Movies>) {
                val movie = gson.fromJson<Movies>(helper.readFromAssetIndex(asset,page,folder),Movies::class.java)
                callback.onResponse(this,Response.success(movie))

            }




        }

    }
}