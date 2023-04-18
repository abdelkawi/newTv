package com.eurisko.alballam.tv.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.Utils
import com.eurisko.alballam.tv.YoutubePlayerActivity
import com.eurisko.alballam.tv.data.model.MovieDetailBean
import com.eurisko.alballam.tv.databinding.ActivityDetailsBinding
import com.eurisko.alballam.tv.ui.MainViewModel
import com.eurisko.alballam.tv.ui.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : FragmentActivity() {
  private var movieDetailBean: MovieDetailBean? = null
  private val mainViewModel: MainViewModel by viewModels()
  lateinit var binding: ActivityDetailsBinding
  private var url = ""
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    movieDetailBean = intent.getParcelableExtra("movie")
    if (movieDetailBean == null) {
      val id = intent.getStringExtra("movieId")
      getOnlineDetails(id ?: "")
    }
    Utils.focusIn(binding.playNow)
    setData()
  }

  private fun setData() {
    binding.tvMovieTitle.text = movieDetailBean?.title
    binding.tvMovieDescription.text = movieDetailBean?.description
    val url = movieDetailBean?.image
    Glide.with(this)
      .load(url)
      .into(binding.ivMoviePhoto)
    var writers = ""
    movieDetailBean?.writerBeans?.forEach {
      writers += it?.name + " "
    }
    if (writers == "") writers = "Unknown"
    binding.tvMovieAuthorValue.text = writers

    var directors = ""
    movieDetailBean?.directorBeans?.forEach {
      directors += it?.name + " "
    }
    if (directors == "") directors = "Unknown"
    binding.tvDirectedByValue.text = directors

    val productionAdapter = ProductionAdapter()
    productionAdapter.submitList(movieDetailBean?.actorBeans)
    binding.productionList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    binding.productionList.adapter = productionAdapter

    if (!movieDetailBean?.season.isNullOrEmpty()) {
      val episodesAdapter = EpisodesAdapter()
      episodesAdapter.submitList(movieDetailBean?.season?.get(0)?.episodes ?: emptyList())
      binding.episodesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      binding.episodesList.adapter = episodesAdapter
    } else {
      binding.btnEpisodes.visibility = View.GONE
    }


    binding.playNow.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus) Utils.focusIn(binding.playNow)
      else Utils.focusOut(binding.playNow)
    }

    binding.btnPromo.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus) Utils.focusIn(binding.btnPromo)
      else Utils.focusOut(binding.btnPromo)
    }
    binding.btnProduction.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus) Utils.focusIn(binding.btnProduction)
      else Utils.focusOut(binding.btnProduction)
    }
    binding.btnFav.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus) Utils.focusIn(binding.btnFav)
      else Utils.focusOut(binding.btnFav)
    }
    binding.btnEpisodes.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus) Utils.focusIn(binding.btnEpisodes)
      else Utils.focusOut(binding.btnEpisodes)
    }
    if (movieDetailBean?.isWatchList == true) {
      binding.btnFav.backgroundTintList = ColorStateList.valueOf(getColor(R.color.back_from_details))
    } else {
      binding.btnFav.backgroundTintList = ColorStateList.valueOf(getColor(R.color.yellow_prim_tv))
    }

    getMovieExtraDetails()
  }

  private fun getMovieExtraDetails() {
    lifecycleScope.launch {
      mainViewModel.getMovieExtraDetail(movieDetailBean?.id ?: "").collect {
        when (it) {
          is DataState -> {
            url = it.data.body()?.movieUrl ?: ""
          }
          is ErrorState -> {
          }
          LoadingState -> {

          }
        }
      }
    }
  }

  private fun getOnlineDetails(id: String) {
    lifecycleScope.launch {
      mainViewModel.getMovie(id).collect() {
        when (it) {
          is DataState -> {
            movieDetailBean = it.data.body()
            setData()
          }
          is ErrorState -> {

          }
          LoadingState -> {

          }
        }
      }
    }
  }

  @SuppressLint("RestrictedApi")
  override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
    val v = currentFocus
    if (event?.keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.action == KeyEvent.ACTION_DOWN) {
      when (v?.id) {
        binding.playNow.id -> {
          val intent = Intent(this, PlayerActivity::class.java)
          intent.putExtra("url", url)
          startActivity(intent)
        }
        binding.btnPromo.id -> {
          val intent = Intent(this, YoutubePlayerActivity::class.java)
          intent.putExtra("trailer", movieDetailBean?.trailer)
          startActivity(intent)
        }
        binding.btnFav.id -> {
          lifecycleScope.launch {
            mainViewModel.addToFav(movieDetailBean?.id ?: "").collect() {
              when (it) {
                is DataState -> {
                  if (movieDetailBean?.isWatchList == true) {
                    binding.btnFav.backgroundTintList = ColorStateList.valueOf(getColor(R.color.back_from_details))
                  } else {
                    binding.btnFav.backgroundTintList = ColorStateList.valueOf(getColor(R.color.yellow_prim_tv))
                  }
                  getOnlineDetails(movieDetailBean?.id ?: "")
                }
                is ErrorState -> {

                }
                LoadingState -> {

                }
              }
            }
          }
        }
        binding.btnProduction.id -> {
          if (binding.productionList.visibility == View.VISIBLE) {
            binding.productionList.visibility = View.GONE
            binding.tvProductionLabel.visibility = View.GONE
          } else {
            if (binding.episodesList.isVisible) {
              binding.episodesList.visibility = View.GONE
              binding.tvEpisodesLabel.visibility = View.GONE
            }
            binding.productionList.visibility = View.VISIBLE
            binding.tvProductionLabel.visibility = View.VISIBLE
            binding.productionList.scrollToPosition(0)
          }

        }
        binding.btnEpisodes.id -> {
          if (binding.episodesList.visibility == View.VISIBLE) {
            binding.episodesList.visibility = View.GONE
            binding.tvEpisodesLabel.visibility = View.GONE
          } else {
            if (binding.productionList.isVisible) {
              binding.productionList.visibility = View.GONE
              binding.tvProductionLabel.visibility = View.GONE
            }
            binding.episodesList.visibility = View.VISIBLE
            binding.tvEpisodesLabel.visibility = View.VISIBLE
            binding.episodesList.scrollToPosition(0)
          }

        }
      }
    }
    return super.dispatchKeyEvent(event)
  }

}