package com.eurisko.alballam.tv.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Season(
  @field:SerializedName("actors")
  val actors: List<ActorBean?>? = null,
  @field:SerializedName("cost")
  val cost: Double? = null,
  @field:SerializedName("directors")
  val directors: List<ActorBean?>? = null,
  @field:SerializedName("bundleId")
  val bundleId: String? = null,
  @field:SerializedName("title")
  val name: String? = null,
  @field:SerializedName("paid")
  val paid: Boolean? = null,
  @field:SerializedName("_id")
  val id: String? = null,
  @field:SerializedName("seasonNumber")
  val seasonNumber: Int? = null,
  @field:SerializedName("writers")
  val writers: List<ActorBean?>? = null,
  @field:SerializedName("episodes")
  val episodes: List<EpisodesItem?>? = null
) : Parcelable

@Parcelize
data class EpisodesItem(
  @field:SerializedName("duration")
  val duration: Int? = null,
  @field:SerializedName("fairPlay")
  val fairPlay: String? = null,
  @field:SerializedName("isPublished")
  val isPublished: Boolean? = null,
  @field:SerializedName("widevine")
  val widevine: String? = null,
  @field:SerializedName("_id")
  val id: String? = null,
  @field:SerializedName("title")
  val title: String? = null,
  @field:SerializedName("dash")
  val dash: String? = null,
  @field:SerializedName("episodeNumber")
  val episodeNumber: Int? = null,
  @field:SerializedName("hls")
  val hls: String? = null
) : Parcelable
