package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginBean(

	@field:SerializedName("data")
	val data: DataSet? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("status")
	val status: Int? = null
) : Parcelable

@Parcelize
data class Message(

	@field:SerializedName("ar")
	val ar: String? = null,

	@field:SerializedName("en")
	val en: String? = null
) : Parcelable

@Parcelize
data class ResultData(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("socketToken")
	val socketToken: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("isAdmin")
	val isAdmin: Boolean? = null,

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("notification")
	val notification: Boolean? = null,

	@field:SerializedName("refresh_token_header")
	val refreshTokenHeader: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("countryCode")
	val countryCode: String? = null,

	@field:SerializedName("isEmail")
	val isEmail: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("phoneCode")
	val phoneCode: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("expires_in")
	val expiresIn: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable

@Parcelize
data class DataSet(

	@field:SerializedName("result")
	val result: ResultData? = null,

	@field:SerializedName("message")
	val message: Message? = null
) : Parcelable
