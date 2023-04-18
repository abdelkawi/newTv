package com.eurisko.alballam.tv.beans

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentBean(
  private var comment: String? = null,
  private var name: String? = null,
  private val imageUrl: String? = null
):Parcelable