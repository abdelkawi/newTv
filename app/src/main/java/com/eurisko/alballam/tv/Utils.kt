package com.eurisko.alballam.tv

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import com.eurisko.alballam.tv.State.ErrorState
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Utils {
  companion object {
    fun resolveError(e: Exception): ErrorState {
      var error = e

      when (e) {
        is SocketTimeoutException -> {
          error = NetworkErrorException(errorMessage = "connection error!")
        }
        is ConnectException -> {
          error = NetworkErrorException(errorMessage = "no internet access!")
        }
        is UnknownHostException -> {
          error = NetworkErrorException(errorMessage = "no internet access!")
        }
      }

      if (e is HttpException) {
        when (e.code()) {
          502 -> {
            error = NetworkErrorException(e.code(), "internal error!")
          }
          401 -> {
            throw AuthenticationException("authentication error!")
          }
          400 -> {
            error = NetworkErrorException.parseException(e)
          }
        }
      }


      return ErrorState(error.message ?: "")
    }

    fun focusIn(v: View) {
      val scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1f, 1.2f)
      val scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1f, 1.2f)
      val set = AnimatorSet()
      set.play(scaleX).with(scaleY)
      set.start()
    }

    fun focusOut(v: View) {
      val scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.2f, 1f)
      val scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.2f, 1f)
      val set = AnimatorSet()
      set.play(scaleX).with(scaleY)
      set.start()
    }
  }


}