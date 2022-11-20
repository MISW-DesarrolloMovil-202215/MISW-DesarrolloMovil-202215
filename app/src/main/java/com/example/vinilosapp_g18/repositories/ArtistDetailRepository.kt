package com.example.vinilosapp_g18.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosapp_g18.models.Artist
import com.example.vinilosapp_g18.network.NetworkServiceAdapter

class ArtistDetailRepository (val application: Application){
    suspend fun refreshData(artistId: Int):List<Artist> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getArtist(artistId)
    }
}