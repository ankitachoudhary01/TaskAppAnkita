package com.example.taskappankita.Repository

import com.example.taskappankita.Retrofit.retrofitService

class photosRepository constructor(private val retrofitService: retrofitService) {
    fun getAllPhotos() = retrofitService.getAllPhotos()
}