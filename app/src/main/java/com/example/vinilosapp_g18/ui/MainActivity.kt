package com.example.vinilosapp_g18.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.content.Intent;
import android.widget.ImageButton
import android.widget.LinearLayout

import androidx.navigation.Navigation

import com.example.vinilosapp_g18.R

class MainActivity : AppCompatActivity() {
    lateinit var bgapp: ImageView;
    lateinit var logo: ImageView;
    lateinit var splashtext: LinearLayout;
    lateinit var homeText: LinearLayout;
    lateinit var menu: LinearLayout;
    lateinit var frombotton: Animation;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frombotton = AnimationUtils.loadAnimation(this, R.anim.frombotton)
        bgapp=findViewById(R.id.backgroundBlack)
        logo=findViewById(R.id.logo)
        splashtext = findViewById(R.id.splashtext)
        homeText = findViewById(R.id.hometext)
        menu = findViewById(R.id.menu)
        bgapp.animate().translationY(-1250f).setDuration(800).setStartDelay(300);
        logo.animate().alpha(0f).setDuration(800).setStartDelay(600);
        splashtext.animate().translationY(140f).alpha(0f).setDuration(800).setStartDelay(300)
        homeText.startAnimation(frombotton)
        menu.startAnimation(frombotton)

        val albumesButton = findViewById<ImageButton>(R.id.albumes)
        val albumesButton2 = findViewById<Button>(R.id.albumes2)
        albumesButton.setOnClickListener {
            val intent = Intent(this, ListAlbumes::class.java)
            startActivity(intent)
        }
        albumesButton2.setOnClickListener {
            val intent = Intent(this, ListAlbumes::class.java)
            startActivity(intent)
        }


        val coleccionistaImageButton = findViewById<ImageButton>(R.id.imgButtoncoleccionista)
        val coleccionistaButton = findViewById<Button>(R.id.btnColeccionistas)
        coleccionistaImageButton.setOnClickListener {
            val intent = Intent(this, ListColecionista::class.java)
            startActivity(intent)
        }
        coleccionistaButton.setOnClickListener {
            val intent = Intent(this, ListColecionista::class.java)
            startActivity(intent)
           // Navigation.findNavController()

        val artistButton = findViewById<ImageButton>(R.id.artistas)
        val artistButton2 = findViewById<Button>(R.id.artistas2)
        artistButton.setOnClickListener {
            val intent = Intent(this, ListArtist::class.java)
            startActivity(intent)
        }
        artistButton2.setOnClickListener {
            val intent = Intent(this, ListArtist::class.java)
            startActivity(intent)

        }
    }
    }
}