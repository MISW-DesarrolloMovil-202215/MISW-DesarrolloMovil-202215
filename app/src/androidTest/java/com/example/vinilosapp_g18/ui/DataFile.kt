package com.example.vinilosapp_g18.ui




data class CustonTrack(
    val nameAlbum:String,
    val nameTrack:String,
    val durationTrack: String
)
{

    fun GetTrack(): CustonTrack {
        var track :CustonTrack
        track= CustonTrack(
            nameAlbum=this.nameAlbum,
            nameTrack=this.nameTrack ,
            durationTrack=this.durationTrack
        )
     return track
    }



}