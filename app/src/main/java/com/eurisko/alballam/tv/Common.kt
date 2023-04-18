package com.eurisko.alballam.tv

import android.content.Context

class Common {
  companion object {
    fun getWidth(context: Context, percent: Int) :Int{
      val width = context.resources.displayMetrics.widthPixels
      return (width * percent) / 100
    }

  }
}
object Constant{
  val HOME= "Home"
  val SEARCH= "Search"
  val SETTINGS= "Settings"
  val FAVORITE= "Favorite"
  val EXIT_APP= "Exit_App"
}