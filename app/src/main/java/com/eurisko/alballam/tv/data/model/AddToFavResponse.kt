package com.eurisko.alballam.tv.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class AddToFavResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable {
  @Parcelize
  data class Message(

    @field:SerializedName("ar")
    val ar: String? = null,

    @field:SerializedName("en")
    val en: String? = null
  ) : Parcelable

  @Parcelize
  data class Data(

    @field:SerializedName("message")
    val message: Message? = null
  ) : Parcelable
}


