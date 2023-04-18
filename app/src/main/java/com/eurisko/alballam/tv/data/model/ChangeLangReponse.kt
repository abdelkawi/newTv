package com.eurisko.alballam.tv.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ChangeLangResponse(

  @field:SerializedName("data")
  val data: Data? = null,

  @field:SerializedName("error")
  val error: Boolean? = null,

  @field:SerializedName("status")
  val status: Int? = null
) : Parcelable {

  @Parcelize
  data class Data(

    @field:SerializedName("message")
    val message: Message? = null
  ) : Parcelable
}
