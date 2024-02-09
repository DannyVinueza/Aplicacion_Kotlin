package com.aplicacion.kotlin.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido a la practica\nSobre aplicaciones moviles con:\n\nFlutter"
    }
    val text: LiveData<String> = _text
}