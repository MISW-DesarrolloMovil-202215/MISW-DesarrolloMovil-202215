package com.example.vinilosapp_g18.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.ColeccionistaDetailItemBinding
import com.example.vinilosapp_g18.models.Coleccionista

class ColeccionistaDetailAdapter : RecyclerView.Adapter<ColeccionistaDetailAdapter.ColeccionistaDetailViewHolder>(){

    var coleccionista :List<Coleccionista> = emptyList()
        set(value) {
            field = value
            notifyItemChanged(0)
            notifyItemInserted(0)
            notifyItemRemoved(0)
            notifyItemRangeChanged(0, -1)
            notifyItemRangeInserted(0, -1)
            notifyItemRangeRemoved(0, -1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionistaDetailViewHolder {
        val withDataBinding: ColeccionistaDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ColeccionistaDetailViewHolder.LAYOUT,
            parent,
            false)
        return ColeccionistaDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ColeccionistaDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.coleccionista = coleccionista[position]
        }
    }

    override fun getItemCount(): Int {
        return coleccionista.size
    }


    class ColeccionistaDetailViewHolder(val viewDataBinding: ColeccionistaDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.coleccionista_detail_item
        }
    }


}