package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailBean(
  @field:SerializedName("_id")
  var id: String? = "",
  @field:SerializedName("type")
  val type: String = "",
  @field:SerializedName("trailer")
  val trailer: String = "",
  @field:SerializedName("image")
  val image: String = "",
  @field:SerializedName("poster")
  val poster: String = "",
  @field:SerializedName("duration")
  val duration: Double = 0.0,
  @field:SerializedName("cost")
  val cost: Double = 0.0,
  @field:SerializedName("title")
  val title: String = "",
  @field:SerializedName("reviews")
  val reviews: Double = 0.0,
  @field:SerializedName("isFavorite")
  val isWatchList: Boolean = false,
  @field:SerializedName("dash")
  val videoSource: String = "",
  @field:SerializedName("progress")
  val progress: Int = 0,
  @field:SerializedName("movieTitle")
  val movieTitle: String = "",
  @field:SerializedName("seasons")
  val season: List<Season?>? = ArrayList(),
  @field:SerializedName("episodeNumber")
  val episodeNumber: String = "",
  @field:SerializedName("description")
  var description: String? = null,
  @field:SerializedName("paid")
  var paid: Boolean = false,
  @field:SerializedName("actors")
  var actorBeans: ArrayList<ActorBean?>? = ArrayList(),
  @field:SerializedName("writers")
  var writerBeans: ArrayList<ActorBean?>? = ArrayList(),
  @field:SerializedName("directors")
  var directorBeans: ArrayList<ActorBean?>? = ArrayList(),
) : Parcelable