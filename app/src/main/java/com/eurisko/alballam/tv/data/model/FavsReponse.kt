package com.eurisko.alballam.tv.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class FavsResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("message")
	val message: Message? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("createdDate")
	val createdDate: String? = null,

	@field:SerializedName("movie")
	val movie: Movie? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("user")
	val user: String? = null,

	@field:SerializedName("isFavorite")
	val isFavorite: Boolean? = null
) : Parcelable

@Parcelize
data class Result(

	@field:SerializedName("pageLimit")
	val pageLimit: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("totalPages")
	val totalPages: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable

@Parcelize
data class Movie(

	@field:SerializedName("trailer")
	val trailer: String? = null,

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

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("cost")
	val cost: String? = null,

	@field:SerializedName("fairPlay")
	val fairPlay: String? = null,

	@field:SerializedName("widevine")
	val widevine: String? = null,

	@field:SerializedName("dash")
	val dash: String? = null,

	@field:SerializedName("hls")
	val hls: String? = null
) : Parcelable
