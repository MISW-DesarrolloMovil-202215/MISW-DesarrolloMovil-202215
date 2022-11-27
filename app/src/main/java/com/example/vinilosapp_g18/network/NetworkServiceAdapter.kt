package com.example.vinilosapp_g18.network


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosapp_g18.models.Album
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.models.Artist
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
   var glbJsonStrPremios:String=""
    var glbJsonStrAlbum:String=""
    companion object{
        const val BASE_URL= "https://back-backvynils-grupo18.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun onCallback2(response: String): String {
        glbJsonStrPremios=response
        return response
    }
    fun onCallback3(response: String): String {
        glbJsonStrAlbum=response
        return response
    }
    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont->
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                var tracks: String= ""
                var comments: String= ""
                var item: JSONObject
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate").split("T").toTypedArray()[0], genre = item.getString("genre"), description = item.getString("description"), tracks = tracks, comments = comments))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getAlbum(albumId:Int)=suspendCoroutine<List<Album>>{cont->
        requestQueue.add(getRequest("albums/$albumId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                var tracks: String=""
                var comments: String=""
                val list = mutableListOf<Album>()
                val arr: JSONArray = resp.getJSONArray("tracks")
                val arrComments: JSONArray = resp.getJSONArray("comments")

                for (i in 0 until arr.length()) {
                    tracks += arr.getJSONObject(i).getString("name") + "    " + arr.getJSONObject(i).getString("duration") + "\n"

                }
                
                for (i in 0 until arrComments.length()) {
                    comments += arrComments.getJSONObject(i).getString("description") + ".\n" + "Calificación: " + arrComments.getJSONObject(i).getString("rating") + "\n" + "\n"


                }

                list.add(0, Album(albumId = resp.getInt("id"),name = resp.getString("name"), cover = resp.getString("cover"), recordLabel = resp.getString("recordLabel"), releaseDate = resp.getString("releaseDate").split("T").toTypedArray()[0], genre = resp.getString("genre"), description = resp.getString("description"), tracks = tracks, comments = comments))
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }


    suspend fun getColeccionistas()=suspendCoroutine<List<Coleccionista>>{cont->
        requestQueue.add(getRequest("collectors",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Coleccionista>()
                var favoritePerformers: String= ""
                var comments: String= ""
                var imagefavoritePerformers: String=""
                var imagecollectorAlbums: String=""
                var collectorAlbums: String=""
                var item: JSONObject
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Coleccionista(coleccionistaId = item.getInt("id"),name = item.getString("name"),telephone= item.getString("telephone"),email= item.getString("email"),comments=comments,imagefavoritePerformers=imagefavoritePerformers,collectorAlbums=collectorAlbums,favoritePerformers=favoritePerformers,imagecollectorAlbums=imagecollectorAlbums))

                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }
    suspend fun getColeccionista(coleccionistaId:Int)=suspendCoroutine<List<Coleccionista>>{cont->
        getAllAlbums()
        requestQueue.add(getRequest("collectors/$coleccionistaId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                var favoritePerformers: String=""
                var imagefavoritePerformers: String=""
                var collectorAlbums: String=""
                var imagecollectorAlbums: String=""
                var comments: String=""
                var collector: String=""
                val list = mutableListOf<Coleccionista>()
                val arrFavorite: JSONArray = resp.getJSONArray("favoritePerformers")
                val arrComments: JSONArray = resp.getJSONArray("comments")
                val arrCollectorAlbums: JSONArray = resp.getJSONArray("collectorAlbums")
                for (i in 0 until arrFavorite.length()) {
                    favoritePerformers += arrFavorite.getJSONObject(i).getString("name") + ".\n" +  "Descripción: " + arrFavorite.getJSONObject(i).getString("description")  + "\n" + "\n"
                    imagefavoritePerformers+=arrFavorite.getJSONObject(i).getString("image")
                }
                var id_album:String
                var nombre_album:String
                var imagen_album:String
                val arrAlbum = JSONArray(glbJsonStrAlbum)
                var _album:JSONObject
                for (i in 0 until arrCollectorAlbums.length()) {
                    id_album=arrCollectorAlbums.getJSONObject(i).getString("id")
                    for (j in 0 until arrAlbum.length()) {
                        _album=arrAlbum.getJSONObject(j)
                        nombre_album=_album.getString("name")
                        imagen_album=_album.getString("cover")
                        if (arrAlbum.getJSONObject(j).getString("id") ==id_album)
                        {
                            collectorAlbums += nombre_album + " " + arrCollectorAlbums.getJSONObject(i).getString("price")+"\n"+arrCollectorAlbums.getJSONObject(i).getString("status")
                            imagecollectorAlbums +=imagen_album
                        }
                    }
                    // prizes += arrPrizesMusico.getJSONObject(i).getString("name") + "\n"
                }
                for (i in 0 until arrComments.length()) {
                    comments += arrComments.getJSONObject(i).getString("description") + ".\n" + "Calificación: " + arrComments.getJSONObject(i).getString("rating") + "\n" + "\n"
                }

                list.add(0, Coleccionista(coleccionistaId = resp.getInt("id"),name = resp.getString("name"), telephone = resp.getString("telephone"), email = resp.getString("email"), imagefavoritePerformers = imagefavoritePerformers, comments = comments,favoritePerformers=favoritePerformers,collectorAlbums=collectorAlbums,imagecollectorAlbums=imagecollectorAlbums))
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }
    private fun getAllAlbums(){
        // Instantiate the RequestQueue.
        val url: String = "https://back-backvynils-grupo18.herokuapp.com/albums"
        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                glbJsonStrAlbum=strResp
            },
            Response.ErrorListener {Log.d("API", "that didn't work") })
        requestQueue.add(stringReq)

    }

    suspend fun getArtists()=suspendCoroutine<List<Artist>>{cont->
        requestQueue.add(getRequest("Musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                var item: JSONObject
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    list.add(i, Artist(artistId = item.getInt("id"),name = item.getString("name"), image = item.getString("image"), birthDate = item.getString("birthDate").split("T").toTypedArray()[0], description = item.getString("description"), albumes = "", prizes = ""))
                }
                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }
    suspend fun getArtist(artistId:Int)=suspendCoroutine<List<Artist>>{cont->
        getAllPrizes()
        requestQueue.add(getRequest("Musicians/$artistId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val list = mutableListOf<Artist>()
                var albumes: String= ""
                var prizes: String= ""
                val arrAlbumes: JSONArray = resp.getJSONArray("albums")
                val arrPrizesMusico: JSONArray = resp.getJSONArray("performerPrizes")



                for (i in 0 until arrAlbumes.length()) {
                    albumes += arrAlbumes.getJSONObject(i).getString("name") + "\n"
                }

                var id_prize:String
                var nombre_premio:String
                var fecha_premio:String
                val arrPremios = JSONArray(glbJsonStrPremios)
                var _premio:JSONObject
                var  arrPrizesMusicos2:JSONArray
                for (i in 0 until arrPrizesMusico.length()) {

                    id_prize=arrPrizesMusico.getJSONObject(i).getString("id")
                    fecha_premio=arrPrizesMusico.getJSONObject(i).getString("premiationDate")

                    for (j in 0 until arrPremios.length()) {
                        _premio=arrPremios.getJSONObject(j)
                        nombre_premio=_premio.getString("name")
                        arrPrizesMusicos2 =JSONArray(_premio.get("performerPrizes").toString())
                        for (k in 0 until arrPrizesMusicos2.length())
                            {
                                if (arrPrizesMusicos2.getJSONObject(k).getString("id") ==id_prize)
                                {
                                        prizes += nombre_premio + " " + arrPrizesMusicos2.getJSONObject(k).getString("premiationDate").split("T").toTypedArray()[0]+"\n"
                                }


                        }

                    }

                   // prizes += arrPrizesMusico.getJSONObject(i).getString("name") + "\n"

                }

                list.add(0, Artist(artistId = resp.getInt("id"),name = resp.getString("name"), image = resp.getString("image"), birthDate = resp.getString("birthDate").split("T").toTypedArray()[0], description = resp.getString("description"), albumes = albumes, prizes = prizes))

                cont.resume(list)
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }

    private fun getAllPrizes(){
        // Instantiate the RequestQueue.
        val url: String = "https://back-backvynils-grupo18.herokuapp.com/prizes"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                glbJsonStrPremios=strResp
            },
            Response.ErrorListener {Log.d("API", "that didn't work") })
        requestQueue.add(stringReq)

    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
            }
