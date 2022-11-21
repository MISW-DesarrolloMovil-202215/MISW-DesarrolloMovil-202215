package com.example.vinilosapp_g18.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.AlbumDetailItemBinding
import com.example.vinilosapp_g18.models.Album

class AlbumDetailAdapter : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>(){

    var album :List<Album> = emptyList()
        set(value) {
            field = value
            notifyItemChanged(0)
            notifyItemInserted(0)
            notifyItemRemoved(0)
            notifyItemRangeChanged(0, -1)
            notifyItemRangeInserted(0, -1)
            notifyItemRangeRemoved(0, -1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumDetailViewHolder.LAYOUT,
            parent,
            false)
        return AlbumDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
          it.album = album[position]
        }
    }

    override fun getItemCount(): Int {
        return album.size
    }


    class AlbumDetailViewHolder(val viewDataBinding: AlbumDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_detail_item
        }
    }


}