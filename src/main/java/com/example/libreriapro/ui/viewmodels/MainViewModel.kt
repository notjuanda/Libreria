package com.example.libreriapro.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.libreriapro.repositories.LibroRepository
import com.example.libreriapro.model.Libros

class MainViewModel : ViewModel() {
    private val _libroList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros()).also {
            Log.d("MainViewModel", "MutableLiveData for libroList initialized")
        }
    }
    val libroList: LiveData<Libros> get() = _libroList

    fun fetchListaLibros() {
        Log.d("MainViewModel", "fetchListaLibros llamado")
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {
                    Log.d("MainViewModel", "fetchListaLibros exitoso, actualizando LiveData")
                    // Ordena los libros por calificación antes de actualizar el LiveData
                    val librosOrdenados = it.sortedByDescending { libro -> libro.calificacion }
                    _libroList.value = ArrayList(librosOrdenados)
                }
            },
            failure = {
                Log.e("MainViewModel", "falla en fetchListaLibros, error: ${it.message}", it)
                it.printStackTrace()
            })
    }
    // En tu MainViewModel
    // En tu MainViewModel
    fun deleteLibro(id: Int) {
        Log.d("MainViewModel", "deleteLibro called with id: $id")
        LibroRepository.deleteLibro(
            id,
            success = {
                Log.d("MainViewModel", "deleteLibro success, updating LiveData")
                fetchListaLibros() // Actualizar la lista de libros después de la eliminación
                Log.d("MainViewModel", "Libro with id: $id deleted successfully")
            },
            failure = {
                Log.e("MainViewModel", "deleteLibro failure, error: ${it.message}", it)
                it.printStackTrace()
            })
    }
}