package com.example.reignchallenge.model.api

import com.example.reignchallenge.model.api.response.Hit
import com.example.reignchallenge.model.api.response.ObjectHits
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HitRepository {

    // GET repo list
    fun getHitList(onResult: (isSuccess: Boolean, response: List<Hit>?) -> Unit) {

        ApiClient.instance.getHitsAsync("android").enqueue(object : Callback<ObjectHits> {
            override fun onResponse(call: Call<ObjectHits>?, response: Response<ObjectHits>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!.hits!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<ObjectHits>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: HitRepository? = null
        fun getInstance() = INSTANCE
            ?: HitRepository().also {
                INSTANCE = it
            }
    }
}