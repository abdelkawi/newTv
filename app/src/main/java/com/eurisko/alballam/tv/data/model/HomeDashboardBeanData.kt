package com.eurisko.alballam.tv.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeDashboardBeanData(
  @field:SerializedName("carousel")
  val carouselBeanList: List<MovieDetailBean>? = null,
  @field:SerializedName("newRelease")
  val newReleaseList: List<MovieDetailBean>? = null,
  @field:SerializedName("shows")
  val showsList: List<MovieDetailBean>? = null,
  @field:SerializedName("plays")
  val playsList: List<MovieDetailBean>? = null,
  @field:SerializedName("mostWatched")
  val mostWatchedBeanList: List<MovieDetailBean>? = null,
  @field:SerializedName("homeAdd")
  val homeAdBean: HomeAdBean? = null
) : Parcelable