package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeDashboardBean(
  @field:SerializedName("data")
  val data: DataSet? = null,

  @field:SerializedName("error")
  val error: Boolean? = null,

  @field:SerializedName("status")
  val status: Int? = null
) : Parcelable {

  @Parcelize
  data class DataSet(
    @field:SerializedName("result")
    val result: HomeDashboardBeanData? = null,

    @field:SerializedName("message")
    val message: Message? = null
  ) : Parcelable
}

