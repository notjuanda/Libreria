package com.example.libreriapro.repositories

import com.example.libreriapro.api.APILibrosService
import com.example.libreriapro.model.Genero
import com.example.libreriapro.model.Generos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneroRepository {

    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val service: APILibrosService = retrofit.create(APILibrosService::class.java)

    fun getGeneroList(success: (List<Genero>?) -> Unit, failure: (Throwable) -> Unit) {
        service.getGeneros().enqueue(object : Callback<List<Genero>> {
            override fun onResponse(call: Call<List<Genero>>, response: Response<List<Genero>>) {
                success(response.body())
            }

            override fun onFailure(call: Call<List<Genero>>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertGenero(genero: Genero, success: (Genero) -> Unit, failure: (Throwable) -> Unit) {
        service.insertGenero(genero).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                response.body()?.let { success(it) } ?: failure(Throwable("Response body is null"))
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getGenero(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        service.getGeneroById(id).enqueue(object : Callback<Genero?> {
            override fun onResponse(call: Call<Genero?>, response: Response<Genero?>) {
                success(response.body())
            }

            override fun onFailure(call: Call<Genero?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateGenero(genero: Genero, success: (Genero) -> Unit, failure: (Throwable) -> Unit) {
        val generoId = genero.id ?: 0
        service.updateGenero(genero, generoId).enqueue(object : Callback<Genero> {
            override fun onResponse(call: Call<Genero>, response: Response<Genero>) {
                response.body()?.let { success(it) } ?: failure(Throwable("Response body is null"))
            }

            override fun onFailure(call: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteGenero(id: Int, success: () -> Unit, failure: (Throwable) -> Unit) {
        service.deleteGenero(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}