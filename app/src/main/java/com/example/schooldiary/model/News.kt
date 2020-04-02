package com.example.schooldiary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class News(var photo: String = "",
           var time: String = "",
           var title: String = "",
           var text: String = "",
           var serialNumber: Int = 0) : Parcelable