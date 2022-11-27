package com.example.vinilosapp_g18.models

data class Coleccionista(
    val coleccionistaId:Int,
    val name:String,
    val telephone :String,
    val email :String,
    val comments:String,
    val favoritePerformers:String,
    val imagefavoritePerformers:String,
    val collectorAlbums:String,
    val imagecollectorAlbums:String
)
