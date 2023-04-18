package com.eurisko.alballam.tv.ui.fav

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.databinding.FragmentFavsBinding
import com.eurisko.alballam.tv.ui.MainViewModel
import com.eurisko.alballam.tv.ui.OnItemClicked
import com.eurisko.alballam.tv.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : FragmentActivity(), OnItemClicked {
  lateinit var binding: FragmentFavsBinding
  private val mainViewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = FragmentFavsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    lifecycleScope.launch {
      mainViewModel.loadFavs().collect() {
        when (it) {
          is DataState -> {
            val adapter = FavItemAdapter(this@FavoriteActivity)
            adapter.submitList(it.data.data?.result?.data?.map { it?.movie })
            binding.rvWatchList.layoutManager = LinearLayoutManager(this@FavoriteActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.rvWatchList.adapter = adapter
            binding.pb.visibility = View.GONE
            binding.rvWatchList.scrollToPosition(0)
            binding.rvWatchList.requestFocus()
          }
          is ErrorState -> {

          }
          LoadingState -> {

          }
        }
      }
    }

  }

  override fun onClicked(id: String) {
    val intent = Intent(this, DetailsActivity::class.java)
    intent.putExtra("movieId", id)
    startActivity(intent)
  }

}


