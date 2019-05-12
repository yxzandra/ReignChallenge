package com.example.reignchallenge.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.reignchallenge.R
import com.example.reignchallenge.viewModel.fragment.HitsViewModel
import com.example.reignchallenge.databinding.FragmentHitsBinding

import java.util.*

class HitsFragment : Fragment(), Observer{
    val TAG = javaClass.name
    private var hitsFragmentBinding: FragmentHitsBinding? = null
    private var hitsViewModel: HitsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hitsFragmentBinding = DataBindingUtil.inflate(inflater ,R.layout.fragment_hits,container , false)
        val myView : View  = hitsFragmentBinding!!.root
        hitsViewModel = context?.let { HitsViewModel(it) }
        hitsFragmentBinding!!.fragmentHitsViewModel = hitsViewModel
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitsViewModel!!.fetchPullList()

    }

    override fun update(p0: Observable?, p1: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}