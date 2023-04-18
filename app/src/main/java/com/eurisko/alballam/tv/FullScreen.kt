package com.eurisko.alballam.tv

import android.os.Bundle
import android.view.WindowManager.LayoutParams
import com.theoplayer.android.api.fullscreen.FullScreenActivity

class FullScreen :FullScreenActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(
      LayoutParams.FLAG_SECURE,
      LayoutParams.FLAG_SECURE
    )
    try {
      window.addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON)
    } catch (e: Exception) {
    }
  }
}