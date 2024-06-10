package com.example.libreriapro.model

typealias Generos = ArrayList<Genero>

data class Genero(
    var nombre: String
) {
    var id: Int? = null
}