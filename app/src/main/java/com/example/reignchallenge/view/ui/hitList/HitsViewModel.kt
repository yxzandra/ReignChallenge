package com.example.reignchallenge.view.ui.hitList

import androidx.lifecycle.MutableLiveData
import com.example.reignchallenge.model.api.HitRepository
import com.example.reignchallenge.model.api.response.Hit
import com.example.reignchallenge.model.dataBase.DataBaseTransaction
import com.example.reignchallenge.model.dataBase.HitTable
import com.example.reignchallenge.util.Helpers
import com.example.reignchallenge.view.base.BaseViewModel

class HitsViewModel : BaseViewModel() {
    val TAG = javaClass.simpleName

    private val dataBaseTransaction = DataBaseTransaction()

    val hitListLive = MutableLiveData<List<HitTable>>()

    fun fetchHitList() {
        dataLoading.value = true
        HitRepository.getInstance().getHitList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                saveHits(response)
                hitListLive.value = dataBaseTransaction.getAll()
                empty.value = false
            } else {
                hitListLive.value = dataBaseTransaction.getAll()
                empty.value = hitListLive.value.isNullOrEmpty()
            }
        }
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
