package com.example.breakingnews.model

import android.os.Parcel
import android.os.Parcelable

data class Berita(
    val title: String,
    val pubDate: String,
    val description: String,
    val thumbnail: String,
    val link: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(pubDate)
        parcel.writeString(description)
        parcel.writeString(thumbnail)
        parcel.writeString(link)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Berita> {
        override fun createFromParcel(parcel: Parcel): Berita {
            return Berita(parcel)
        }

        override fun newArray(size: Int): Array<Berita?> {
            return arrayOfNulls(size)
            }
        }
}