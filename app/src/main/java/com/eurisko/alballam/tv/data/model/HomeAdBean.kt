package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeAdBean(
  var id: String? = "" ,
  var countries: ArrayList<String?>? = ArrayList(),
  var active: Boolean = false,
  var name: String? = "",
  var url: String? = "",
  var createdDate: String? = "",
  var createdBy: String? = "",
  var image: String? = ""
) :Parcelable