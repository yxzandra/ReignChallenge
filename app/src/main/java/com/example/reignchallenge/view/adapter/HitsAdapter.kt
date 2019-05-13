package com.example.reignchallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.R
import com.example.reignchallenge.databinding.CardHitsBinding
import com.example.reignchallenge.schema.Hit
import com.example.reignchallenge.viewModel.itemAdapter.ItemViewModel

class HitsAdapter(private val fragmentManager: FragmentManager) : RecyclerView.Adapter<HitsAdapter.HitsAdapterViewHolder>() {
    val TAG = javaClass.simpleName
    private var pullList: MutableList<Hit>? = null

    override fun getItemCount(): Int {
        if (pullList == null)
            return 0
        return pullList!!.size
    }

    internal fun setPullList(pullList: List<Hit>) {
        this.pullList = pullList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HitsAdapterViewHolder, position: Int) {
        holder.bindPull(pullList!![position], fragmentManager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsAdapterViewHolder {
        val itemPeopleBinding: CardHitsBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.card_hits,
            parent, false)

        return HitsAdapterViewHolder(itemPeopleBinding)
    }

    fun removeAt(position: Int) {
        pullList?.removeAt(position)
        notifyItemRemoved(position)
    }

    class HitsAdapterViewHolder(
        var mItemHitsModel: CardHitsBinding
    ) :
        RecyclerView.ViewHolder(mItemHitsModel.cardHits) {

        fun bindPull(pullItem: Hit, fragmentManager: FragmentManager) {
            mItemHitsModel.hitsViewModel =
                ItemViewModel(pullItem, fragmentManager)
        }
    }
}
