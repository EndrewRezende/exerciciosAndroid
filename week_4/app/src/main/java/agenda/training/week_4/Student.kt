package com.example.week_4

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student (
    val id: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val email: String,
    var course: String,
    var shift: String) : Parcelable