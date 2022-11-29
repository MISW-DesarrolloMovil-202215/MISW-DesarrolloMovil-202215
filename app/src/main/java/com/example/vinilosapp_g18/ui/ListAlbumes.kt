package com.example.vinilosapp_g18.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.vinilosapp_g18.CreateAlbum
import com.example.vinilosapp_g18.CreateTrack
import com.example.vinilosapp_g18.R



class ListAlbumes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_albumes)

        val btnCreateAlbum = findViewById<Button>(R.id.btnCreateAlbum)
        btnCreateAlbum.setOnClickListener {
            val intent = Intent(this, CreateAlbum::class.java)
            startActivity(intent)
            finish()
        }

        val btnCreateAlbum2 = findViewById<ImageButton>(R.id.btnCreateAlbum2)
        btnCreateAlbum2.setOnClickListener {
            val intent = Intent(this, CreateAlbum::class.java)
            startActivity(intent)
            finish()
        }



        val btnCreateTrack = findViewById<Button>(R.id.btnCreateAlbum)
        btnCreateTrack.setOnClickListener {
            val intent = Intent(this, CreateTrack::class.java)
            startActivity(intent)
            finish()
        }

        val btnCreateTrack2 = findViewById<ImageButton>(R.id.btnImgCreateTrack)
        btnCreateTrack2.setOnClickListener {
            val intent = Intent(this, CreateTrack::class.java)
            startActivity(intent)
            finish()
        }




        // val albumes = mutableListOf("Album 1", "Album 2", "Album 3", "Album 4", "Album 5", "Album 6")
      //  val lvAlbumes = findViewById<ListView>(R.id.lvAlbumes)

       // arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumes)
        //lvAlbumes.adapter = arrayAdapter

    }


}