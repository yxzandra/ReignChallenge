package com.example.reignchallenge.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.databinding.CardHitsBinding
import com.example.reignchallenge.model.dataBase.DataBaseTransaction
import com.example.reignchallenge.model.dataBase.HitTable

class HitsAdapter : RecyclerView.Adapter<HitAdapterViewHolder>() {
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

    override fun onBindViewHolder(holder: HitAdapterViewHolder, position: Int) {
        holder.setup(hitListTable!![position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = CardHitsBinding.inflate(inflater, parent, false)
        return HitAdapterViewHolder(dataBinding)
    }

    fun removeAt(position: Int) {
        val dataBaseTransaction = DataBaseTransaction()
        dataBaseTransaction.deleteHit(hitListTable!![position].id)
        hitListTable?.removeAt(position)
        notifyItemRemoved(position)
    }


}
