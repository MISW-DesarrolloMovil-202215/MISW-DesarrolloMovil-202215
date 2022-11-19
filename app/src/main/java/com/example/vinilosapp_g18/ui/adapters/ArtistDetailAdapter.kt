package com.example.vinilosapp_g18.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.ArtistDetailItemBinding
import com.example.vinilosapp_g18.models.Artist

class ArtistDetailAdapter : RecyclerView.Adapter<ArtistDetailAdapter.ArtistDetailViewHolder>(){

    var artist :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyItemChanged(0)
            notifyItemInserted(0)
            notifyItemRemoved(0)
            notifyItemRangeChanged(0, -1)
            notifyItemRangeInserted(0, -1)
            notifyItemRangeRemoved(0, -1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistDetailViewHolder {
        val withDataBinding: ArtistDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistDetailViewHolder.LAYOUT,
            parent,
            false)
        return ArtistDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = artist[position]
        }
    }

    override fun getItemCount(): Int {
        return artist.size
    }


    class ArtistDetailViewHolder(val viewDataBinding: ArtistDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_detail_item
        }
    }


}