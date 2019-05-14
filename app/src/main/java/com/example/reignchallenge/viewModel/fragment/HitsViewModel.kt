package com.example.reignchallenge.viewModel.fragment

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.reignchallenge.api.ApiInterface
import com.example.reignchallenge.api.RetrofitClient
import com.example.reignchallenge.dataBase.DataBaseTransaction
import com.example.reignchallenge.api.response.Hit
import com.example.reignchallenge.api.response.ObjectHits
import com.example.reignchallenge.dataBase.HitTable
import com.example.reignchallenge.util.Helpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class HitsViewModel() : Observable() {
    val TAG = javaClass.simpleName

    var hitsProgress: ObservableInt = ObservableInt(View.VISIBLE)
    var hitsRecycler: ObservableInt = ObservableInt(View.INVISIBLE)
    var hitsLabel: ObservableInt = ObservableInt(View.INVISIBLE)
    var messageLabel: ObservableField<String> = ObservableField("")

    var hitList : List<HitTable>? = null
    private val dataBaseTransaction = DataBaseTransaction()

    fun fetchPullList(context: Context) {

        if (!Helpers.isNetworkAvailable(context)) {
            if (hitList.isNullOrEmpty())
                setList()

            hitsProgress.set(View.GONE)
            hitsLabel.set(View.GONE)
            hitsRecycler.set(View.VISIBLE)

        }else
            loadListHits()
    }

    private fun loadListHits() {
        val apiService = RetrofitClient.getClient().create(ApiInterface::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                apiService.getHitsAsync("android").await().let {
                    hitsProgress.set(View.GONE)

                    if (it.isSuccessful && it.body() != null) {
                        hitsLabel.set(View.GONE)
                        hitsRecycler.set(View.VISIBLE)
                        saveHits(it.body()!!.hits)
                        setList()
                    } else {
                        hitsLabel.set(View.VISIBLE)
                        messageLabel.set("Problem conection")
                    }

                }
            } catch (exception: Exception) {
                Log.e(TAG, "Failed to fetch data!")
            }
        }

    }

    private fun setList() {
        setChanged()
        hitList = dataBaseTransaction.getAll()
        this.notifyObservers()
    }

    private fun saveHits(hits: List<Hit>?) {
        hits?.forEach {
            dataBaseTransaction.addHit(id = it.objectID!!,author = it.author!!,createAt = it.createdAt!!,title = getTitle(it), url = getUrl(it))
        }
    }

    private fun getUrl(hit: Hit): String{
        return when {
            !hit.storyUrl.isNullOrEmpty() -> {
                return hit.storyUrl!!
            }
            !hit.url.isNullOrEmpty() -> {
                return hit.url!!
            }
            else -> ""
        }
    }

    private fun getTitle(hit: Hit): String{
        return when{
            !hit.storyTitle.isNullOrEmpty() -> hit.storyTitle!!
            !hit.title.isNullOrEmpty() -> hit.title!!
            else -> ""

        }
    }

}
