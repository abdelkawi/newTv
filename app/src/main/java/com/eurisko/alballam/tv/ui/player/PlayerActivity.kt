package com.eurisko.alballam.tv.ui.player

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.view.WindowManager.LayoutParams
import android.webkit.WebView
import androidx.fragment.app.FragmentActivity
import com.eurisko.alballam.tv.FullScreen
import com.eurisko.alballam.tv.databinding.ActivityPlayerBinding
import com.theoplayer.android.api.THEOplayerConfig.Builder
import com.theoplayer.android.api.THEOplayerGlobal
import com.theoplayer.android.api.THEOplayerView
import com.theoplayer.android.api.event.ads.AdBeginEvent
import com.theoplayer.android.api.event.ads.AdsEventTypes
import com.theoplayer.android.api.event.player.ErrorEvent
import com.theoplayer.android.api.event.player.PlayerEventTypes
import com.theoplayer.android.api.source.SourceDescription
import com.theoplayer.android.api.source.TypedSource


class PlayerActivity : FragmentActivity(), OnKeyListener {
  var theoPlayerView: THEOplayerView? = null
  var theoPlayerGlobal: THEOplayerGlobal? = null
  var streamLink: String? = ""
  var playNextEpisode = false
  var activity: Activity? = null
  lateinit var binding: ActivityPlayerBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(
      LayoutParams.FLAG_SECURE,
      LayoutParams.FLAG_SECURE
    )
    binding = ActivityPlayerBinding.inflate(layoutInflater)
    window.addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON)
    activity = this
    setupViews()
    implementViews()
  }

  private fun setupViews() {
    val playerConfig = Builder()
      .cssPaths("style1.css", "font-awesome.min.css")
      .jsPaths("script1.js")
      .build()
    theoPlayerView = THEOplayerView(this, playerConfig)
    theoPlayerView!!.fullScreenManager.fullscreenActivity = FullScreen::class.java
    theoPlayerView!!.player.addEventListener<ErrorEvent>(PlayerEventTypes.ERROR) { errorEvent: ErrorEvent ->
      theoPlayerView!!.evaluateJavaScript("error", null)
    }
    streamLink = intent.getStringExtra("url")
    setContentView(theoPlayerView)

  }

  private fun implementViews() {
    setupVideoPlayer()
  }

  private fun setupVideoPlayer() {
    WebView.setWebContentsDebuggingEnabled(true)
    theoPlayerGlobal = THEOplayerGlobal.getSharedInstance(this)
    theoPlayerView!!.settings.isFullScreenOrientationCoupled = true
    setPlayerSource()
  }

  private fun setPlayerSource() {
    val typedSource = TypedSource.Builder
      .typedSource()
      .src(streamLink ?: "")
      .build()

    val sourceDescription = SourceDescription.Builder
      .sourceDescription(typedSource)
      .build()
    theoPlayerView!!.player.source = sourceDescription
    theoPlayerView!!.player.isAutoplay = true
    theoPlayerView!!.player.ads.addEventListener<AdBeginEvent>(AdsEventTypes.AD_BEGIN) { adBeginEvent: AdBeginEvent ->
      try {
        val adId = adBeginEvent.ad!!.id
        getAdNameFromId(adId)
      } catch (e: Exception) {
      }
    }
  }

  private fun setPlayerListener() {
    theoPlayerView!!.player.addEventListener(PlayerEventTypes.ENDED) {
      playNextEpisode = true
      Handler().postDelayed({ playNextEpisode() }, 2000)
    }
    theoPlayerView!!.player.addEventListener(
      PlayerEventTypes.PLAY
    ) { playNextEpisode = false }
  }

  private fun playNextEpisode() {

  }


  override fun onPause() {
    super.onPause()
    try {
      if (theoPlayerView != null) {
        theoPlayerView!!.onPause()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun onResume() {
    super.onResume()
    try {
      if (theoPlayerView != null) {
        theoPlayerView!!.onResume()
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }


  override fun onDestroy() {
    super.onDestroy()
    theoPlayerView!!.onDestroy()
  }

  override fun onBackPressed() {
    super.onBackPressed()
  }


  private fun getAdNameFromId(id: String) {

  }

  override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {

    return false
  }

  @SuppressLint("RestrictedApi")
  override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
    if (event?.keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.action == KeyEvent.ACTION_DOWN) {
      if (theoPlayerView?.player?.isPaused == true) {
        theoPlayerView?.player?.play()
      } else {
        theoPlayerView?.player?.pause()
      }
    }
    if (event?.keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.action == KeyEvent.ACTION_DOWN) {
      theoPlayerView?.player?.requestCurrentTime { currentTime ->
        theoPlayerView?.player?.setCurrentTime(currentTime + 10)
      }
    }
    if (event?.keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.action == KeyEvent.ACTION_DOWN) {
      theoPlayerView?.player?.requestCurrentTime { currentTime ->
        theoPlayerView?.player?.setCurrentTime(currentTime - 10)
      }
    }
    return super.dispatchKeyEvent(event)
  }

}