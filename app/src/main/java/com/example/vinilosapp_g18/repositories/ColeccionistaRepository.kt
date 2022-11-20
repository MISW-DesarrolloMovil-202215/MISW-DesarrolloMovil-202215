package com.example.vinilosapp_g18.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.network.NetworkServiceAdapter

class ColeccionistaRepository (val application: Application){
    suspend fun refreshData():List<Coleccionista> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        return NetworkServiceAdapter.getInstance(application).getColeccionistas()
    }
}