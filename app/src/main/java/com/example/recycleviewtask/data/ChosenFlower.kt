package com.example.recycleviewtask.data

import android.os.Parcel
import android.os.Parcelable

class ChosenFlower() : Parcelable {
    var sourceImageFlower: String? = null
    var descriptionFlower: String? = null

    constructor(parcel: Parcel) : this() {
        sourceImageFlower = parcel.readString()
        descriptionFlower = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
parcel.writeString(sourceImageFlower)
        parcel.writeString(descriptionFlower)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChosenFlower> {
        override fun createFromParcel(parcel: Parcel): ChosenFlower {
            return ChosenFlower(parcel)
        }

        override fun newArray(size: Int): Array<ChosenFlower?> {
            return arrayOfNulls(size)
        }
    }
}
