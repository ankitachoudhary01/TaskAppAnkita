package com.example.taskappankita.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskappankita.Model.photosModel
import com.example.taskappankita.Retrofit.onClickInterface
import com.example.taskappankita.databinding.AdapterPhotosBinding

class photosAdapter(val onClickInterface: onClickInterface): RecyclerView.Adapter<PhotosViewHolder>() {

    var photos = mutableListOf<photosModel>()
    lateinit var onClickinterface : onClickInterface
    fun setPhotosList(photos: List<photosModel>) {
        this.photos = photos.toMutableList()
        this.onClickinterface = onClickInterface
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterPhotosBinding.inflate(inflater, parent, false)
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = photos[position]
        holder.binding.name.text = photo.title
        Glide.with(holder.itemView.context).load(photo.url).into(holder.binding.imageview)
        holder.itemView.setOnClickListener {
            onClickInterface.onclick(position,photo)
        }


    }

    override fun getItemCount(): Int {
        return photos.size
    }
}

class PhotosViewHolder(val binding: AdapterPhotosBinding) : RecyclerView.ViewHolder(binding.root) {

}


