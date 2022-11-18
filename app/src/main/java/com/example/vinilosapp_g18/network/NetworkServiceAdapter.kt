package com.example.vinilosapp_g18.network


import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilosapp_g18.models.Album
import com.example.vinilosapp_g18.models.Artist
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter constructor(context: Context) {
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
    fun getAlbums(onComplete:(resp:List<Album>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                var tracks: String
                tracks = ""
                var comments: String
                comments = ""
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description"), tracks = tracks, comments = comments))


                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun getAlbum(albumId:Int, onComplete:(resp:List<Album>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("albums/$albumId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val list = mutableListOf<Album>()
                var tracks: String
                tracks = ""
                var comments: String
                comments = ""
                val arr: JSONArray = resp.getJSONArray("tracks")
                val arrComments: JSONArray = resp.getJSONArray("comments")

                for (i in 0 until arr.length()) {
                    tracks += arr.getJSONObject(i).getString("name") + "    " + arr.getJSONObject(i).getString("duration") + "\n"

                }
                
                for (i in 0 until arrComments.length()) {
                    comments += arrComments.getJSONObject(i).getString("description") + ".\n" + "Calificación: " + arrComments.getJSONObject(i).getString("rating") + "\n" + "\n"


                }

                list.add(0, Album(albumId = resp.getInt("id"),name = resp.getString("name"), cover = resp.getString("cover"), recordLabel = resp.getString("recordLabel"), releaseDate = resp.getString("releaseDate"), genre = resp.getString("genre"), description = resp.getString("description"), tracks = tracks, comments = comments))

                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun getArtists(onComplete:(resp:List<Artist>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("Musicians",
            Response.Listener<String> { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Artist(artistId = item.getInt("id"),name = item.getString("name"), image = item.getString("image"), birthDate = item.getString("birthDate").split("T").toTypedArray()[0], description = item.getString("description"), albumes = "", prizes = ""))
                }
                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }
    fun getArtist(artistId:Int, onComplete:(resp:List<Artist>)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("Musicians/$artistId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val list = mutableListOf<Artist>()
                var albumes: String
                albumes= ""
                var prizes: String
                prizes= ""

                val arrAlbumes: JSONArray = resp.getJSONArray("albums")
                val arrPrizes: JSONArray = resp.getJSONArray("performerPrizes")

                for (i in 0 until arrAlbumes.length()) {
                    albumes += arrAlbumes.getJSONObject(i).getString("name") + "\n"

                }

                /*for (i in 0 until arrPrizes.length()) {
                    prizes += arrPrizes.getJSONObject(i).getString("name") + "\n"

                }*/

                list.add(0, Artist(artistId = resp.getInt("id"),name = resp.getString("name"), image = resp.getString("image"), birthDate = resp.getString("birthDate").split("T").toTypedArray()[0], description = resp.getString("description"), albumes = albumes, prizes = prizes))

                onComplete(list)
            },
            Response.ErrorListener {
                onError(it)
            }))
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