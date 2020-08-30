package com.diagnal.test.services

import com.diagnal.test.models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET
    fun fetchData(@Query("page")  page: Int,@Query("folder")  folder: String):Call<Movies>
}