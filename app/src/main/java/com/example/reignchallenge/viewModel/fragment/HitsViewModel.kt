package com.example.reignchallenge.viewModel.fragment;

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reignchallenge.api.ApiInterface
import com.example.reignchallenge.api.RetrofitClient
import com.example.reignchallenge.schema.ObjectHits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class HitsViewModel(var mContext:Context): Observable() {
    val TAG = javaClass.simpleName

    var hitsProgress: ObservableInt = ObservableInt(View.VISIBLE)
    var hitsRecycler: ObservableInt = ObservableInt(View.INVISIBLE)
    var hitsLabel: ObservableInt = ObservableInt(View.INVISIBLE)
    var messageLabel: ObservableField<String> = ObservableField("")

    private lateinit var hitsObjectLiveData: MutableLiveData<ObjectHits>

    fun fetchPullList(): LiveData<ObjectHits> {
        hitsObjectLiveData = MutableLiveData()
        loadListHits()
        return hitsObjectLiveData
    }

    private fun loadListHits() {
        val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)
        GlobalScope.launch ( Dispatchers.Main) {
            try {
                apiService.getHits("android", 0).await().let {
                    hitsProgress.set(View.GONE)

                    if (it.isSuccessful && it.body() != null){
                        hitsLabel.set(View.GONE)
                        hitsRecycler.set(View.VISIBLE)
                    }else {
                        hitsLabel.set(View.VISIBLE)
                        messageLabel.set("Problem conection")
                    }

                }
            }catch (exception : Exception) {
                Log.e(TAG, "Failed to fetch data!")
            }
        }
    }

}