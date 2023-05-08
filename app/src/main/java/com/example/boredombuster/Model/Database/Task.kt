package com.example.boredombuster.Model.Database

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val key: String,
    @ColumnInfo (name = "activity")
    val activity: String,
    @ColumnInfo (name = "type")
    val type: String,
    @ColumnInfo (name = "participants")
    val participants: Int,
    @ColumnInfo (name = "price")
    val price: Double,
    @ColumnInfo (name = "link")
    val link: String,
    @ColumnInfo (name = "accessibility")
    val accessibility: Int,
    @ColumnInfo (name = "favorite")
    var favorite: Boolean,
    ) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readBoolean()
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(activity)
        parcel.writeString(type)
        parcel.writeInt(participants)
        parcel.writeDouble(price)
        parcel.writeString(link)
        parcel.writeInt(accessibility)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Task> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }
        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}