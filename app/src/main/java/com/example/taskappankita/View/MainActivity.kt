package com.example.taskappankita.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskappankita.Adapter.photosAdapter
import com.example.taskappankita.Model.photosModel
import com.example.taskappankita.R
import com.example.taskappankita.Repository.photosRepository
import com.example.taskappankita.Retrofit.onClickInterface
import com.example.taskappankita.Retrofit.retrofitService
import com.example.taskappankita.ViewModel.photosViewModel
import com.example.taskappankita.databinding.ActivityMainBinding
import com.example.taskappankita.photoViewModelFactory

class MainActivity : AppCompatActivity(), onClickInterface {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: photosViewModel
    private val RetrofitService = retrofitService.getInstance()
    val adapter = photosAdapter(this)
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, photoViewModelFactory(photosRepository(RetrofitService!!)))
            .get(photosViewModel::class.java)

        binding.recyclerview.adapter = adapter

        if (viewModel.checkForInternet(this)) {
            getAllData()
            binding.txtRetry.visibility = View.GONE

        } else {
            Toast.makeText(this, "check for internet connection", Toast.LENGTH_SHORT).show()
            binding.txtRetry.visibility = View.VISIBLE
        }

        binding.txtRetry.setOnClickListener {
            if (viewModel.checkForInternet(this)) {
              getAllData()
                binding.txtRetry.visibility = View.GONE
            }else{
                binding.txtRetry.visibility = View.VISIBLE

            }

        }
    }

    override fun onclick(position: Int, photos: photosModel) {
        val intent: Intent = Intent(this, Detailactivity::class.java)
        intent.putExtra("photo", photos)
        startActivity(intent)
    }

    fun getAllData(){
        viewModel.photoList.observe(this, Observer {
            binding.progressbar.visibility = View.VISIBLE

            handler.postDelayed(Runnable {
                adapter.setPhotosList(it)
                binding.progressbar.visibility = View.GONE

            }, 1000)

        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            binding.progressbar.visibility = View.GONE
        })
        viewModel.getAllPhotos()

    }
}