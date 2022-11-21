package com.example.vinilosapp_g18.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.ColeccionistaItemBinding
import com.example.vinilosapp_g18.models.Coleccionista


class ColeccionistaAdapter : RecyclerView.Adapter<ColeccionistaAdapter.ColeccionistaViewHolder>(){

    var colecionista :List<Coleccionista> = emptyList()
        set(value) {
            field = value
            notifyItemChanged(0)
            notifyItemInserted(0)
            notifyItemRemoved(0)
            notifyItemRangeChanged(0, -1)
            notifyItemRangeInserted(0, -1)
            notifyItemRangeRemoved(0, -1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionistaViewHolder {
        val withDataBinding: ColeccionistaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ColeccionistaViewHolder.LAYOUT,
            parent,
            false)
        return ColeccionistaViewHolder(withDataBinding)
    }
//TODO
    override fun onBindViewHolder(holder: ColeccionistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.coleccionista= colecionista[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
           //val action = AlbumFragmentDirections.actionAlbumFragmentToAlbumDetailFragment(colecionista[position].coleccionistaId)
            // Navigate using that action
            //holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return colecionista.size
    }


    class ColeccionistaViewHolder(val viewDataBinding: ColeccionistaItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.coleccionista_item
        }
    }

}