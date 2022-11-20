package com.example.vinilosapp_g18.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.network.NetworkServiceAdapter
import com.example.vinilosapp_g18.repositories.ColeccionistaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ColeccionistaViewModel(application: Application) :  AndroidViewModel(application) {

    private val coleccionistaRepository = ColeccionistaRepository(application)

    private val _coleccionistas = MutableLiveData<List<Coleccionista>>()

    val coleccionistas: LiveData<List<Coleccionista>>
        get() = _coleccionistas

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = coleccionistaRepository.refreshData()
                    _coleccionistas.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }catch (e:Exception){
                _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ColeccionistaViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ColeccionistaViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
