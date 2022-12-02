package com.example.vinilosapp_g18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.vinilosapp_g18.models.Album
import com.example.vinilosapp_g18.network.NetworkServiceAdapter
import com.example.vinilosapp_g18.viewmodels.AlbumViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.*
import org.json.JSONObject


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
                Log.d("jsonAlbunes","tiene algo")
                Log.d("jsonAlbunes2",listAlbum!!.count().toString())

                ConfigurarComboAlbumes(listAlbum!!,false)
            }
            else
            {
                Log.d("jsonAlbunes","null")
            }

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
            //expresion regular para evaluar input de la duracion (5:00-99:59)
            val patter=Regex("([01]?[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9]):[0-5][0-9]")
            if (trackName.trim().isEmpty())
            {
                txtTrackName.requestFocus()
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
                txtTracDuration.requestFocus()
                msgError +="* Debe ingresar una valor para la duración de la pista"+"\n"
                error=true

            }else if(! patter.matches(trackDuration))
                {
                    txtTracDuration.requestFocus()
                    msgError +="* Duración no valida ej. 3:50,99:59"+"\n"
                    error=true
                }

            if(error) {
                Toast.makeText(this@CreateTrack, msgError, Toast.LENGTH_LONG).show()
            }
            else
            {
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

        val listAlbumes2: List<Album> = listAlbumes
        val adaptadorDataSpinner:ArrayAdapter<Album> =ArrayAdapter<Album>(this,android.R.layout.simple_spinner_item,listAlbumes)
        adaptadorDataSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        this@CreateTrack.runOnUiThread(java.lang.Runnable {
        val spinner=this.findViewById<Spinner>(R.id.album_spinner)
        val textTrackAlbum=this.findViewById<TextView>(R.id.text_tracks_album)
        val imageViewCoverAlbum=this.findViewById<ImageView>(R.id.coverAlbumTrack_ImageView)


        spinner.adapter=adaptadorDataSpinner
                spinner.onItemSelectedListener=object :
                    AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        Glide.with(this@CreateTrack).load(listAlbumes2[position].cover).into(imageViewCoverAlbum)
                        textTrackAlbum.setText(listAlbumes2[position].tracks)
                        albumSeleccionado=listAlbumes2[position]

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
    }).start()
    }


}