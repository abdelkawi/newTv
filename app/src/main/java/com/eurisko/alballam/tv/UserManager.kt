package com.eurisko.alballam.tv

import android.content.SharedPreferences
import com.eurisko.alballam.tv.data.model.LoginBean
import com.google.gson.Gson
import javax.inject.Inject

class UserManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
  fun saveUserData(loginBean: LoginBean) {
    val editor = sharedPreferences.edit()
    editor.putString("userData", Gson().toJson(loginBean))
    editor.apply()
  }

  fun getUserData(): LoginBean? {
    return Gson().fromJson(sharedPreferences.getString("userData", ""), LoginBean::class.java)
  }

  fun saveUserToken(token: String) {
    val editor = sharedPreferences.edit()
    editor.putString("token", "")
    editor.apply()
  }

}