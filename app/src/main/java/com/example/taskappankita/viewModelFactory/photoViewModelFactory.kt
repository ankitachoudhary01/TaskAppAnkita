package com.example.taskappankita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskappankita.Repository.photosRepository
import com.example.taskappankita.ViewModel.photosViewModel

class photoViewModelFactory constructor(private val repository: photosRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(photosViewModel::class.java)) {
            photosViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}




