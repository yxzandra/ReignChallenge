package com.example.reignchallenge.model.api

import com.example.reignchallenge.model.api.response.ObjectHits
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //Get Hits list for date
    @GET("search_by_date")
    fun getHitsAsync(
        @Query("query") platform: String): Call<ObjectHits>
}

