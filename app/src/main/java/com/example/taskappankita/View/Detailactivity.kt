package com.example.taskappankita.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.taskappankita.Model.photosModel
import com.example.taskappankita.R
import com.example.taskappankita.databinding.ActivityMainBinding
import com.example.taskappankita.databinding.DetailLayoutBinding

class Detailactivity : AppCompatActivity() {
    private lateinit var binding: DetailLayoutBinding
    lateinit var photosModel: photosModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        photosModel = intent.getParcelableExtra("photo")!!
        Log.e("Tag","photosModelsss"+photosModel)
        Glide.with(this).load(photosModel.url).into(binding.imgDetail)
        binding.txtTitle.text = photosModel.title


    }
}