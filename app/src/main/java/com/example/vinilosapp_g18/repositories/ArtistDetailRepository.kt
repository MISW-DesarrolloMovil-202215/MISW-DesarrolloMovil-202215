package com.example.vinilosapp_g18.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosapp_g18.models.Artist
import com.example.vinilosapp_g18.network.NetworkServiceAdapter

class ArtistDetailRepository (val application: Application){
    fun refreshData(artistId: Int, callback: (List<Artist>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getArtist(artistId,{
            //Guardar los coleccionistas de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}