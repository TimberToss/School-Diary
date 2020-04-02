package com.example.schooldiary.model.subject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject(var name: String = "",
                   var homework: String = "",
                   var teacher: String = "",
                   var classroom: String = "",
                   var serialNumber: Int = 0) : Parcelable