package com.example.reignchallenge.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.databinding.FragmentHitsBinding
import com.example.reignchallenge.util.SwipeToDeleteCallback
import com.example.reignchallenge.view.adapter.HitsAdapter
import com.example.reignchallenge.viewModel.fragment.HitsViewModel
import kotlinx.android.synthetic.main.fragment_hits.*
import java.util.*


class HitsFragment : Fragment(), Observer{
    val TAG = javaClass.name
    private var hitsFragmentBinding: FragmentHitsBinding? = null
    private var hitsViewModel: HitsViewModel? = null
    private var adapter: HitsAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hitsFragmentBinding = DataBindingUtil.inflate(inflater ,
            com.example.reignchallenge.R.layout.fragment_hits,container , false)
        val myView : View  = hitsFragmentBinding!!.root
        hitsViewModel = context?.let { HitsViewModel() }
        hitsFragmentBinding!!.fragmentHitsViewModel = hitsViewModel
        return myView
    }


    private fun setRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            hitsViewModel!!.fetchPullList()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitsViewModel!!.addObserver(this)
        hitsViewModel!!.fetchPullList()
        setRefreshListener()
    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p0 == hitsViewModel){
            recycler_hits.adapter = adapter
            recycler_hits.layoutManager = LinearLayoutManager(context)
            adapter = HitsAdapter(activity!!.supportFragmentManager)
            adapter!!.setPullList(hitsViewModel!!.hitList!!)
            recycler_hits.adapter = adapter
            val swipeHandler = object : SwipeToDeleteCallback(context!!) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = recycler_hits.adapter as HitsAdapter
                    adapter.removeAt(viewHolder.adapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(recycler_hits)

            if (swipeRefreshLayout.isRefreshing){
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

}