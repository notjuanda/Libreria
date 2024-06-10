package com.example.libreriapro.repositories

import com.example.libreriapro.api.APILibrosService
import com.example.libreriapro.model.LibroGenero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LibroGeneroRepository {

    private val retrofit = RetrofitRepository.getRetrofitInstance()
    private val service: APILibrosService = retrofit.create(APILibrosService::class.java)

    fun addGeneroToLibro(libroGenero: LibroGenero, success: (LibroGenero) -> Unit, failure: (Throwable) -> Unit) {
        service.addGeneroToLibro(libroGenero).enqueue(object : Callback<LibroGenero> {
            override fun onResponse(call: Call<LibroGenero>, response: Response<LibroGenero>) {
                response.body()?.let { success(it) } ?: failure(Throwable("Response body is null"))
            }

            override fun onFailure(call: Call<LibroGenero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun removeGeneroFromLibro(libroGenero: LibroGenero, success: () -> Unit, failure: (Throwable) -> Unit) {
        service.removeGeneroFromLibro(libroGenero).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }

}