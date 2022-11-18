package com.example.vinilosapp_g18.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilosapp_g18.models.Artist
import com.example.vinilosapp_g18.repositories.ArtistDetailRepository

class ArtistDetailViewModel (application: Application, artistId: Int) :  AndroidViewModel(application) {

    private val artistRepository = ArtistDetailRepository(application)

    private val _artist = MutableLiveData<List<Artist>>()

    val artist: LiveData<List<Artist>>
        get() = _artist

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = artistId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        artistRepository.refreshData(id, {
            _artist.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            Log.d("Error", it.toString())
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val artistId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistDetailViewModel(app, artistId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}