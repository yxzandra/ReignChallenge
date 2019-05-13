package com.example.reignchallenge.api

import com.example.reignchallenge.api.response.ObjectHits
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //Get Hits list for date
    @GET("search_by_date")
    fun getHitsAsync(
        @Query("query") platform: String): Deferred<Response<ObjectHits>>
}

