package com.example.vinilosapp_g18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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
import org.json.JSONObject
import kotlin.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.json.JSONStringer


class CreateTrack : AppCompatActivity() {
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    private lateinit var viewModel: AlbumViewModel
   lateinit var  albumSeleccionado:Album
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_track)




        var listAlbum: List<Album>? = null

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



                ConfigurarComboAlbumes(listAlbum!!,false)



            }
            else
            {
            Log.d("jsonAlbunes","null")
            }

            Log.d("FIN","FIN")

    }



        ///region Configuracion Boton Crear Tranck
        var exitosoCrearTrack:Boolean=false
        val btnCreateAlbum = findViewById<Button>(R.id.btn_newAlbum)
        val txtTrackName=findViewById<EditText>(R.id.trackName_text_input)
        val txtTracDuration=findViewById<EditText>(R.id.trackDuration_text_input)
        txtTrackName.setText("")
        txtTracDuration.setText("")

        btnCreateAlbum.setOnClickListener {

            val trackName :String= txtTrackName.text.toString().trim()
            val albumId :String= this.findViewById<Spinner>(R.id.album_spinner).selectedItemId.toString()
            val albumNamew :String= this.findViewById<Spinner>(R.id.album_spinner).selectedItem.toString()
            val trackDuration:String=txtTracDuration.text.toString().trim()
            var msgError:String=""
            var error:Boolean=false
            val patter=Regex("([01]?[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9]):[0-5][0-9]")
            if (trackName.trim().isEmpty())
            {
                msgError="* El nombre del track no puede estar vacio"+"\n"
                error=true
            }
            if (albumId.trim().isEmpty())
            {
                msgError +="* Debe seleccionar un album"+"\n"
                error=true
            }

            if (trackDuration.isNullOrEmpty())
            {
                msgError +="* Debe ingresar una valor para la duración de la pista"+"\n"
                error=true

            }else if(! patter.matches(trackDuration))
                {

                    msgError +="* Duración no valida ej. 3:50,99:59"+"\n"
                    error=true
                }

            if(error) {
                Toast.makeText(this@CreateTrack, msgError, Toast.LENGTH_LONG).show()
            }
            else
            {

                val msg:String= trackDuration + "-"+trackName +"-"+ albumId.toString()+"-" +albumNamew +"sel:"+ albumSeleccionado.albumId.toString()

                //Toast.makeText(this@CreateTrack,msg,Toast.LENGTH_LONG).show()


                GlobalScope.launch {
                    val trackAlbum = JSONObject();
                    trackAlbum.put("name", trackName)
                    trackAlbum.put("duration", trackDuration)

                    Log.d("Antes Button POST BODY", trackAlbum["name"].toString())
                    Log.d("albumSelecc.albumId",albumSeleccionado.albumId.toString())
                    var returnNewTrack=  NetworkServiceAdapter.getInstance(application).postTrackToAlbum(trackAlbum,albumSeleccionado.albumId.toString())
                    Log.d("Despues Button POST","Despues Button")

                    Log.d("Idtrack",returnNewTrack["id"].toString())
                    if(returnNewTrack != null && returnNewTrack["id"] != null)
                    {
                        exitosoCrearTrack=true;
                        txtTrackName.setText("")
                        txtTracDuration.setText("")
                    }

                    listAlbum=  NetworkServiceAdapter.getInstance(application).getAlbums()
                    ConfigurarComboAlbumes(listAlbum!!,true)
                }

                if(exitosoCrearTrack)
                {
                    Toast.makeText(this@CreateTrack,"**Track agregado con éxito **",Toast.LENGTH_LONG).show()
                }



        }
    }

     ///endregion

    }

    fun ConfigurarComboAlbumes(listAlbumes: List<Album>, SeleccionarItemSpinner:Boolean){
        Thread(Runnable {
        Log.d("function","ff")
        Log.d("function1","ff1")
        val lista= listOf<String>("","","","")
        val listAlbumes2: List<Album> = listAlbumes
        val adaptadorDataSpinner:ArrayAdapter<Album> =ArrayAdapter<Album>(this,android.R.layout.simple_spinner_item,listAlbumes)
        adaptadorDataSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


          this@CreateTrack.runOnUiThread(java.lang.Runnable {
        val spinner=this.findViewById<Spinner>(R.id.album_spinner)
        val textTrackAlbum=this.findViewById<TextView>(R.id.text_tracks_album)
        //val imageViewCoverAlbum=this.findViewById<TextView>(R.id.album_cover_imageDetail)

        spinner.adapter=adaptadorDataSpinner
                spinner.onItemSelectedListener=object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                     //   Glide.with(this@CreateTrack).load(listAlbumes2[position].cover).into(imageViewCoverAlbum)
                        //region configuracion trackAlbum

                        textTrackAlbum.setText(listAlbumes2[position].tracks)
                        albumSeleccionado=listAlbumes2[position]

                        //endregion

                       //Toast.makeText(this@CreateTrack,listAlbumes2[position].tracks.toString(),Toast.LENGTH_LONG).show()
                       // Toast.makeText(this@CreateTrack,listAlbumes2[position].tracks.toString(),Toast.LENGTH_LONG).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }
              //Configura en el spiner el albumseleccionado nuevamente pero con la data actualizada
              if(SeleccionarItemSpinner)
              {
                  val albumsel:Album=listAlbumes!!.filter { it.albumId==albumSeleccionado.albumId}.get(0)
                  val spinnerPosition: Int = adaptadorDataSpinner.getPosition(albumsel)
                  spinner.setSelection(spinnerPosition)

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