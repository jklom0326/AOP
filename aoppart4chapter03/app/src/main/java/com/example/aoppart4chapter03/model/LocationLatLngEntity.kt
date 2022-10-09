package com.example.aoppart4chapter03.model

import android.os.Parcelable

@Parcelize
data class LocationLatLngEntity(
    val lattitude: Float,
    val longitude: Float
) : Parcelable
