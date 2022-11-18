package com.example.vinilosapp_g18.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vinilosapp_g18.R


import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class ListArtist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_artist)
        val arrayAdapter: ArrayAdapter<*>
        // val albumes = mutableListOf("Album 1", "Album 2", "Album 3", "Album 4", "Album 5", "Album 6")
        //  val lvAlbumes = findViewById<ListView>(R.id.lvAlbumes)

        // arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumes)
        //lvAlbumes.adapter = arrayAdapter
    }
}