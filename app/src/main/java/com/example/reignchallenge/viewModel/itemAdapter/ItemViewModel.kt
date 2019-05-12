package com.example.reignchallenge.viewModel.itemAdapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import com.example.reignchallenge.schema.Hit
import com.example.reignchallenge.util.Helpers



class ItemPullViewModel(var mHitsItem: Hit): BaseObservable() {
    val TAG = javaClass.simpleName

    fun hitsTitle(): String{
        return when {
            mHitsItem.storyTitle!!.isNotEmpty() -> mHitsItem.storyTitle!!
            mHitsItem.title!!.isNotEmpty() -> mHitsItem.title!!
            else -> ""
        }
    }

    fun hitsSubtitle(): String{
        var subtitle = ""
        
        if (mHitsItem.author!!.isNotEmpty())
            subtitle = mHitsItem.author!! + " - "
        
        if (mHitsItem.createdAt!!.isNotEmpty())
            subtitle += Helpers.convertDate(mHitsItem.createdAt!!)

        return subtitle
        
    }

    fun onItemClick(view: View) {
        when {
            mHitsItem.storyUrl!!.isNotEmpty() -> {
                Log.i(TAG, mHitsItem.storyUrl)
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mHitsItem.storyUrl))
                view.context.startActivity(browserIntent)
            }
            mHitsItem.url!!.isNotEmpty() -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mHitsItem.url))
                view.context.startActivity(browserIntent)
            }
            else -> Toast.makeText(
                view.context, "Not conains URL",
                Toast.LENGTH_LONG
            ).show()
        }
    }


}

