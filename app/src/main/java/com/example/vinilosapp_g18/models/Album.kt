package com.example.vinilosapp_g18.models

import android.R.id


data class Album(
val albumId:Int,
val name:String,
val cover:String,
val releaseDate:String,
val description:String,
val genre:String,
val recordLabel:String,
val tracks:String,
val comments:String

)
{

    override fun toString(): String {
       return  name
   }


       public fun getid():Int{
        return albumId
    }
}
