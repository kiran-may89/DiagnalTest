package com.diagnal.test.repository

import androidx.lifecycle.MutableLiveData
import com.diagnal.test.services.Services
import com.diagnal.test.models.Movies
import com.diagnal.test.models.NetWorkState
import com.diagnal.test.models.Success
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

class DataRepository @Inject constructor(val services: Services) {

    fun fetchData(
        page: Int,
        folder: String,
        movieLiveData: MutableLiveData<NetWorkState>
    ){
       val call = services.fetchData(page,folder)
        call.enqueue(object : Callback<Movies>{
            override fun onFailure(call: Call<Movies>, t: Throwable) {

            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val state = response.body()?.let { Success(it.page.content_items.content) };
                movieLiveData.value = state
            }

        })
    }
}