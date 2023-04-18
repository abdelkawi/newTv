package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItem(
  var id: String? = "",
  val type: String = "",
  val trailer: String = "",
  val image: String = "",
  val poster: String = "",
  val duration: Double = 0.0,
  val cost: Double = 0.0,
  val title: String = "",
  val reviews: Double = 0.0,
  val isWatchList: Boolean = false,
  val videoSource: String = "",
  val progress: Int = 0,
  val movieTitle: String = "",
  val season: String = "",
  val episodeNumber: String = ""
) : Parcelable
