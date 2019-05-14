package com.example.reignchallenge.view.ui.hitList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.databinding.FragmentHitsBinding
import com.example.reignchallenge.util.Helpers
import com.example.reignchallenge.util.SwipeToDeleteCallback
import com.example.reignchallenge.view.adapter.HitsAdapter
import kotlinx.android.synthetic.main.fragment_hits.*
import org.jetbrains.anko.longToast


class HitsFragment : Fragment(){
    val TAG = javaClass.name
    private lateinit var hitsFragmentBinding: FragmentHitsBinding
    private lateinit var adapter: HitsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        hitsFragmentBinding = FragmentHitsBinding.inflate(inflater, container, false).apply {
            fragmentHitsViewModel = ViewModelProviders.of(this@HitsFragment).get(HitsViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return hitsFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitsFragmentBinding.fragmentHitsViewModel?.fetchHitList(swipeRefreshLayout.isRefreshing)

        setupAdapter()
        setupObservers()
        setRefreshListener()
    }

    private fun setRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener {
            if (!Helpers.isNetworkAvailable(context!!)&& hitsFragmentBinding.fragmentHitsViewModel!!.hitListLive.value.isNullOrEmpty()|| Helpers.isNetworkAvailable(context!!))
                hitsFragmentBinding.fragmentHitsViewModel!!.fetchHitList(swipeRefreshLayout.isRefreshing)
            else
                swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupObservers() {
        hitsFragmentBinding.fragmentHitsViewModel?.hitListLive?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.setHitList(it)
           if (swipeRefreshLayout.isRefreshing) {swipeRefreshLayout.isRefreshing = false}
        })
        hitsFragmentBinding.fragmentHitsViewModel?.toastMessage?.observe(viewLifecycleOwner,androidx.lifecycle.Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        val viewModel = hitsFragmentBinding.fragmentHitsViewModel
        if (viewModel != null) {
            adapter = HitsAdapter()
            val layoutManager = LinearLayoutManager(activity)
            recycler_hits.layoutManager = layoutManager
            recycler_hits.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
            recycler_hits.adapter = adapter

            val swipeHandler = object : SwipeToDeleteCallback(context!!) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = recycler_hits.adapter as HitsAdapter
                    adapter.removeAt(viewHolder.adapterPosition)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(recycler_hits)

        }
    }
}