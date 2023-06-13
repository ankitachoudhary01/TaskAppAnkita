package com.example.taskappankita.Retrofit

import com.example.taskappankita.Model.photosModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface retrofitService {

    @GET("photos")
    fun getAllPhotos() : Call<List<photosModel>>

    companion object {

        var RetrofitService: retrofitService? = null

        fun getInstance() : retrofitService {

            if (RetrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                RetrofitService = retrofit.create(retrofitService::class.java)
            }
            return RetrofitService!!
        }
    }

}