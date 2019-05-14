package com.example.reignchallenge.view.ui.hitDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_hits_detail.*
import android.webkit.WebView
import com.example.reignchallenge.R
import android.webkit.WebChromeClient


class HitsDetailFragment : Fragment(){
    val TAG = javaClass.simpleName
    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hits_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.let { HitsDetailFragmentArgs.fromBundle(it).url }
        webHitsDetail.webViewClient= WebViewClient()
        webHitsDetail.settings.setSupportZoom(true)
        webHitsDetail.settings.javaScriptEnabled = true
        webHitsDetail.webChromeClient = WebChromeClient()

        webHitsDetail.settings.builtInZoomControls = true


        if (url!!.contains(".pdf"))
            webHitsDetail.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
        else
            webHitsDetail.loadUrl(url)

        webHitsDetail.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                progress_hits_detail?.visibility = View.GONE
                webHitsDetail?.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webHitsDetail.stopLoading()
    }
}