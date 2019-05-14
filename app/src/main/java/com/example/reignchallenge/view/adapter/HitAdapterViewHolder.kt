package com.example.reignchallenge.view.adapter

import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.reignchallenge.BR
import com.example.reignchallenge.R
import com.example.reignchallenge.model.dataBase.HitTable
import com.example.reignchallenge.util.Helpers
import kotlinx.android.synthetic.main.card_hits.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.sdk27.coroutines.onClick

class HitAdapterViewHolder constructor(private val dataBinding: ViewDataBinding)
    : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(itemData: HitTable) {
        dataBinding.setVariable(BR.hitsData, itemData)
        dataBinding.executePendingBindings()
        itemView.author_text.text = Helpers.hitsSubtitle(itemData)

        itemView.onClick {
            val bundle = bundleOf("url" to itemData.url)
            itemView.findNavController().navigate(R.id.action_hitListFragment_to_hitDetailFragment, bundle)
        }
    }
}