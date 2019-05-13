package com.example.reignchallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.R
import com.example.reignchallenge.databinding.CardHitsBinding
import com.example.reignchallenge.dataBase.DataBaseTransaction
import com.example.reignchallenge.dataBase.HitTable
import com.example.reignchallenge.viewModel.itemAdapter.ItemViewModel

class HitsAdapter(private val fragmentManager: FragmentManager) : RecyclerView.Adapter<HitsAdapter.HitsAdapterViewHolder>() {
    val TAG = javaClass.simpleName
    private var hitListTable: MutableList<HitTable>? = null

    override fun getItemCount(): Int {
        if (hitListTable == null)
            return 0
        return hitListTable!!.size
    }

    internal fun setHitList(hitListTable: List<HitTable>) {
        this.hitListTable = hitListTable.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HitsAdapterViewHolder, position: Int) {
        holder.bindHit(hitListTable!![position], fragmentManager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsAdapterViewHolder {
        val itemPeopleBinding: CardHitsBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.context), R.layout.card_hits,
            parent, false)

        return HitsAdapterViewHolder(itemPeopleBinding)
    }

    fun removeAt(position: Int) {
        val dataBaseTransaction = DataBaseTransaction()
        dataBaseTransaction.deleteHit(hitListTable!![position].id)
        hitListTable?.removeAt(position)
        notifyItemRemoved(position)
    }

    class HitsAdapterViewHolder(
        var mItemHitsModel: CardHitsBinding
    ) :
        RecyclerView.ViewHolder(mItemHitsModel.cardHits) {

        fun bindHit(hitItem: HitTable, fragmentManager: FragmentManager) {
            mItemHitsModel.hitsViewModel =
                ItemViewModel(hitItem, fragmentManager)
        }
    }
}
