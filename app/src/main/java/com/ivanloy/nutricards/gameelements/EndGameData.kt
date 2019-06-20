package com.ivanloy.nutricards.gameelements

import android.os.Parcel
import android.os.Parcelable

class EndGameData() : Parcelable {
    var playerApples = ""
    var iaApples = ""

    var playerOnion = ""
    var iaOnion = ""

    var playerMeatFish = ""
    var iaMeatFish = ""

    var playerDonut = ""
    var iaDonut = ""

    var playerMilk = ""
    var iaMilk = ""

    var playerCereal = ""
    var iaCereal = ""

    var playerTotal = ""
    var iaTotal = ""

    var message = ""

    constructor(parcel: Parcel) : this() {
        playerApples = parcel.readString()
        iaApples = parcel.readString()
        playerOnion = parcel.readString()
        iaOnion = parcel.readString()
        playerMeatFish = parcel.readString()
        iaMeatFish = parcel.readString()
        playerDonut = parcel.readString()
        iaDonut = parcel.readString()
        playerMilk = parcel.readString()
        iaMilk = parcel.readString()
        playerCereal = parcel.readString()
        iaCereal = parcel.readString()
        playerTotal = parcel.readString()
        iaTotal = parcel.readString()
        message = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(playerApples)
        parcel.writeString(iaApples)
        parcel.writeString(playerOnion)
        parcel.writeString(iaOnion)
        parcel.writeString(playerMeatFish)
        parcel.writeString(iaMeatFish)
        parcel.writeString(playerDonut)
        parcel.writeString(iaDonut)
        parcel.writeString(playerMilk)
        parcel.writeString(iaMilk)
        parcel.writeString(playerCereal)
        parcel.writeString(iaCereal)
        parcel.writeString(playerTotal)
        parcel.writeString(iaTotal)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EndGameData> {
        override fun createFromParcel(parcel: Parcel): EndGameData {
            return EndGameData(parcel)
        }

        override fun newArray(size: Int): Array<EndGameData?> {
            return arrayOfNulls(size)
        }
    }
}