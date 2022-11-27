package com.example.vinilosapp_g18.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilosapp_g18.models.Coleccionista
import com.example.vinilosapp_g18.repositories.ColeccionistaDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColeccionistaDetailViewModel (application: Application, coleccionistaId: Int) :  AndroidViewModel(application) {

    private val coleccionistaRepository = ColeccionistaDetailRepository(application)

    private val _coleccionista = MutableLiveData<List<Coleccionista>>()

    val coleccionista: LiveData<List<Coleccionista>>
        get() = _coleccionista

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = coleccionistaId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = coleccionistaRepository.refreshData(id)
                    _coleccionista.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }catch (e:Exception) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val coleccionistaId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ColeccionistaDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ColeccionistaDetailViewModel(app, coleccionistaId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}