package com.example.vinilosapp_g18.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilosapp_g18.R
import com.example.vinilosapp_g18.databinding.ArtistItemBinding
import com.example.vinilosapp_g18.models.Artist
import com.example.vinilosapp_g18.ui.ArtistFragmentDirections

class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>(){

    var artists :List<Artist> = emptyList()
        set(value) {
            field = value
            notifyItemChanged(0)
            notifyItemInserted(0)
            notifyItemRemoved(0)
            notifyItemRangeChanged(0, -1)
            notifyItemRangeInserted(0, -1)
            notifyItemRangeRemoved(0, -1)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistViewHolder.LAYOUT,
            parent,
            false)
        return ArtistViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist= artists[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            val action = ArtistFragmentDirections.actionArtistFragmentToArtistDetailFragment(artists[position].artistId)
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return artists.size
    }


    class ArtistViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }
    }

}