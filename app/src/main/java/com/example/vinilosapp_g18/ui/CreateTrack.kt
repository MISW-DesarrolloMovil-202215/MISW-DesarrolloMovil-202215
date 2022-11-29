package com.example.vinilosapp_g18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.vinilosapp_g18.models.Album
import com.example.vinilosapp_g18.network.NetworkServiceAdapter
import com.example.vinilosapp_g18.ui.ListAlbumes
import com.example.vinilosapp_g18.viewmodels.AlbumViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlin.coroutines.resume
import kotlinx.coroutines.*
import kotlin.coroutines.*


class CreateTrack : AppCompatActivity() {
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_track)

      //  viewModel = ViewModelProvider(this, AlbumViewModel.Factory(this.application)).get(AlbumViewModel::class.java)


        var listAlbum: List<Album>? = null
        val lista= listOf<String>("","","","")
       // val adaptadorDataSpinner=ArrayAdapter(this,android.R.layout.simple_spinner_item,lista)

        GlobalScope.launch {
            listAlbum=  NetworkServiceAdapter.getInstance(application).getAlbums()
            val o=1+1
            if(listAlbum != null)
            {
                val ourCoroutineContext = ThreadLocal<CoroutineContext>()
                val listNameAlbum= listAlbum!!.map { it.name }
                val listIdsAlbum= listAlbum!!.map { it.albumId }

             //   val adaptadorDataSpinner=ArrayAdapter(CoroutineScope ,android.R.layout.simple_spinner_item,lista)

                Log.d("jsonAlbunes","tiene algo")
                Log.d("jsonAlbunes2",listAlbum!!.count().toString())
                Log.d("listNameAlbum",listNameAlbum!!.count().toString())



                ff(listNameAlbum,listIdsAlbum, listAlbum!!)
            }
            else
            {
            Log.d("jsonAlbunes","null")
            }

            Log.d("FIN","FIN")

    }



    }

    fun ff(listNombreAlbumes: List<String>,listIdAlbumes: List<Int>,listAlbumes: List<Album>){
        Thread(Runnable {
        Log.d("function","ff")
        Log.d("function1","ff1")
        val lista= listOf<String>("","","","")
        val listAlbumes2: List<Album> = listAlbumes
        val adaptadorDataSpinner:ArrayAdapter<Album> =ArrayAdapter<Album>(this,android.R.layout.simple_spinner_item,listAlbumes)
        adaptadorDataSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Log.d("Xfunction1","Xff1X")

            this@CreateTrack.runOnUiThread(java.lang.Runnable {

        val spinner=this.findViewById<Spinner>(R.id.album_spinner)
        val textTrackAlbum=this.findViewById<TextView>(R.id.text_tracks_album)
        Log.d("X2function1","Xff1X2")

        spinner.adapter=adaptadorDataSpinner
                spinner.onItemSelectedListener=object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                      //region configuracion trackAlbum

                            textTrackAlbum.setText(listAlbumes2[position].tracks)

                        //endregion

                       Toast.makeText(this@CreateTrack,listAlbumes2[position].tracks.toString(),Toast.LENGTH_LONG).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
            })
        Log.d("X3function1","Xff1X3")
    }).start()
    }

   suspend fun ff1(){

        Log.d("function1","ff1")
       val lista= listOf<String>("","","","")
        val adaptadorDataSpinner=ArrayAdapter(this,android.R.layout.simple_spinner_item,lista)
    }
}