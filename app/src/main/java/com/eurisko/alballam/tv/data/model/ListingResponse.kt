package com.eurisko.alballam.tv.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ListingResponse(

	@field:SerializedName("data")
	val data: ListData? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class ListItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("reviews")
	val reviews: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("createDate")
	val createDate: String? = null,

	@field:SerializedName("isFavorite")
	val isFavorite: Boolean? = null
) : Parcelable



@Parcelize
data class ListData(

	@field:SerializedName("result")
	val result: ListResult? = null,

	@field:SerializedName("message")
	val message: Message? = null
) : Parcelable

@Parcelize
data class ListResult(

	@field:SerializedName("pageLimit")
	val pageLimit: Int? = null,

	@field:SerializedName("data")
	val data: List<ListItem?>? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable
