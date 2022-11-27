package com.example.vinilosapp_g18.repositories

import android.app.Application
import com.example.vinilosapp_g18.models.Artist
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.network.NetworkServiceAdapter

class ColeccionistaDetailRepository (val application: Application){
    suspend fun refreshData(coleccionistaId: Int):List<Coleccionista> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getColeccionista(coleccionistaId)
    }
}