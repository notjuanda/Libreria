package com.example.libreriapro.model

typealias LibrosGeneros = ArrayList<LibroGenero>

data class LibroGenero(
    var idLibro: Int,
    var idGenero: Int
) {
    var id: Int? = null
}