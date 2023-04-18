package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieAdBean(
  @field:SerializedName("ads")
  val adsBeans: List<AdsBean>,
  @field:SerializedName("url")
  val movieUrl: String = ""
) : Parcelable

@Parcelize
class AdsBean(
  @field:SerializedName("_id")
  val _id: String? = "",
  @field:SerializedName("vastLink")
  val vastLink: String? = "",
  @field:SerializedName("time")
  val time: Int = 0,
  @field:SerializedName("addName")
  val name: String? = "",
  @field:SerializedName("add")
  val adId: String? = "",
) : Parcelable
