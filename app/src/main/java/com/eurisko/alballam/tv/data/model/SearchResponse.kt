package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SearchResponse(
  @field:SerializedName("data")
  val data: DataSet? = null,

  @field:SerializedName("error")
  val error: Boolean? = null,

  @field:SerializedName("status")
  val status: Int? = null
) {
  @Parcelize
  data class DataSet(
    @field:SerializedName("result")
    val result: Items?=null,

    @field:SerializedName("message")
    val message: Message? = null
  ) : Parcelable

  @Parcelize
  data class Items(
    @field:SerializedName("data")
    val data: List<SearchItem>? = null
  ) : Parcelable
}