package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActorBean(
  @field:SerializedName("_id")
  var id: String?= "",
  @field:SerializedName("name")
  var name: String? = "",
  @field:SerializedName("profile")
  var profile: String? = "",
) : Parcelable
