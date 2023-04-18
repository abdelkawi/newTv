package com.eurisko.alballam.tv.ui.mainscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.eurisko.alballam.tv.DataModel
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.data.model.MovieDetailBean
import com.eurisko.alballam.tv.databinding.FragmentHomeBinding
import com.eurisko.alballam.tv.ui.details.DetailsActivity
import com.eurisko.alballam.tv.ui.MainViewModel
import com.eurisko.alballam.tv.ui.listing.ListingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
  lateinit var binding: FragmentHomeBinding
  private val mainViewModel: MainViewModel by viewModels()
  lateinit var listFragment: ListFragment
  private var currentMovieDetailBean: MovieDetailBean? = null


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentHomeBinding.bind(view)
    init()
  }

  private fun init() {
    listFragment = ListFragment()
    val transaction = childFragmentManager.beginTransaction()
    transaction.add(R.id.list_fragment, listFragment)
    transaction.commit()
    lifecycleScope.launch {
      mainViewModel.loadHomeContent()
      mainViewModel.homeData.observe(requireActivity()) {
        when (it) {
          is DataState -> {
            listFragment.bindData(DataModel(it.data))
            autoScroll(it.data?.data?.result?.carouselBeanList ?: emptyList())
          }
          is ErrorState -> {

          }
          LoadingState -> {

          }
        }
      }
    }
    binding.moreDetailsTv.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
        val intent = Intent(requireContext(), DetailsActivity::class.java)
        intent.putExtra("movie", currentMovieDetailBean)
        startActivity(intent)
      }
      false
    }
    binding.allTv.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
        val intent = Intent(requireContext(), ListingActivity::class.java)
        intent.putExtra("type", "all")
        intent.putExtra("isNewRelease", false)
        startActivity(intent)
      }
      false
    }
    binding.mostWatchedTv.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
        val intent = Intent(requireContext(), ListingActivity::class.java)
        intent.putExtra("type", "all")
        intent.putExtra("isNewRelease", false)
        startActivity(intent)
      }
      false
    }
    binding.newReleaseTv.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
        val intent = Intent(requireContext(), ListingActivity::class.java)
        intent.putExtra("type", "shows")
        intent.putExtra("isNewRelease", true)
        startActivity(intent)
      }
      false
    }

    listFragment.setOnItemClickListener {
      val intent = Intent(requireContext(), DetailsActivity::class.java)
      intent.putExtra("movie", it)
      startActivity(intent)
    }
  }

  override fun onResume() {
    super.onResume()
    binding.allTv.requestLayout()
  }

  private fun updateBanner(movieDetailBean: MovieDetailBean) {
    binding.description.text = movieDetailBean.description
    val url = movieDetailBean.image
    Glide.with(requireContext()).load(url).into(binding.imgBanner)
    Glide.with(requireContext()).load(movieDetailBean.poster).into(binding.ivMoviePhoto)
  }

  private fun autoScroll(movies: List<MovieDetailBean>) {
    val handler = Handler()
    var scrollPosition = 0
    val runnable = object : Runnable {
      override fun run() {
        if (scrollPosition == movies.size) scrollPosition = 0
        currentMovieDetailBean = movies.get(scrollPosition++)
        updateBanner(currentMovieDetailBean!!)
        handler.postDelayed(this, 5000)
      }
    }
    handler.post(runnable)
  }
}