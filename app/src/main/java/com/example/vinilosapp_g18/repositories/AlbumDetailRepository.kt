package com.example.vinilosapp_g18.repositories

import android.app.Application
import com.example.vinilosapp_g18.models.Album
import com.example.vinilosapp_g18.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application){
    suspend fun refreshData(albumId: Int):List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getAlbum(albumId)
    }
}