package com.example.taskappankita.Model

import android.os.Parcel
import android.os.Parcelable

data class photosModel(
    val albumId: String?, val id: String?,
    val title: String?, val url: String?, val thumbnailUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(albumId)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<photosModel> {
        override fun createFromParcel(parcel: Parcel): photosModel {
            return photosModel(parcel)
        }

        override fun newArray(size: Int): Array<photosModel?> {
            return arrayOfNulls(size)
        }
    }
}


