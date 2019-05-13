package com.example.reignchallenge.viewModel.itemAdapter

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.reignchallenge.R
import com.example.reignchallenge.schema.Hit
import com.example.reignchallenge.util.Helpers
import com.example.reignchallenge.view.fragment.HitsDetailFragment
import com.example.reignchallenge.view.fragment.HitsFragment


class ItemViewModel(var mHitsItem: Hit, var fragmentManager: FragmentManager): BaseObservable() {
    val TAG = javaClass.simpleName

    fun hitsTitle(): String{
        return when{
            !mHitsItem.storyTitle.isNullOrEmpty() -> mHitsItem.storyTitle!!
            !mHitsItem.title.isNullOrEmpty() -> mHitsItem.title!!
            else -> ""

        }
    }

    fun hitsSubtitle(): String{
        var subtitle = ""
        
        if (mHitsItem.author!!.isNotEmpty())
            subtitle = mHitsItem.author!!
        
        if (mHitsItem.createdAt!!.isNotEmpty())
            subtitle += " - " + Helpers.differenceWithCurrentDate(mHitsItem.createdAt!!)

        return subtitle
        
    }

    fun onItemClick(view: View) {
        when {
            !mHitsItem.storyUrl.isNullOrEmpty() -> {
                Log.i(TAG, mHitsItem.storyUrl)
                openWebViewFragment(mHitsItem.storyUrl!!, view)
            }
            !mHitsItem.url.isNullOrEmpty() -> {
                openWebViewFragment(mHitsItem.url!!,view)
            }
            else -> Toast.makeText(
                view.context, "Not conains URL",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun openWebViewFragment(url: String, view : View) {
        val bundle = Bundle()
        bundle.putString(view.context.getString(R.string.bundle_url), url)
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val repositoryFragment = HitsDetailFragment()
        repositoryFragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment_container,repositoryFragment)
            .addToBackStack(HitsFragment().TAG)
            .commit()
    }


}

