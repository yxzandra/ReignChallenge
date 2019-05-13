package com.example.reignchallenge.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.reignchallenge.R
import kotlinx.android.synthetic.main.fragment_hits_detail.*

class HitsDetailFragment : Fragment(){
    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hits_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url = arguments!!.getString(getString(R.string.bundle_url))
        webHitsDetail.webViewClient= WebViewClient()
        webHitsDetail.loadUrl(url)
    }
}