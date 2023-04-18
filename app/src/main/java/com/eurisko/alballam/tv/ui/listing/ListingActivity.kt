package com.eurisko.alballam.tv.ui.listing

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.data.model.ListItem
import com.eurisko.alballam.tv.databinding.FragmentListingBinding
import com.eurisko.alballam.tv.ui.MainViewModel
import com.eurisko.alballam.tv.ui.OnItemClicked
import com.eurisko.alballam.tv.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListingActivity : FragmentActivity(), OnItemClicked {
  private val mainViewModel: MainViewModel by viewModels()
  lateinit var binding: FragmentListingBinding
  private var itemClickListener: ((ListItem) -> Unit)? = null
  private val dataList = ArrayList<ListItem>()
  lateinit var adapter: ListingItemAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = FragmentListingBinding.inflate(layoutInflater)
    setContentView(binding.root)
    adapter = ListingItemAdapter(this)
    binding.listRv.layoutManager = GridLayoutManager(this, 4)
    binding.listRv.adapter = adapter
    if ((intent.getStringExtra("type") ?: "") == "all") {
      getData("show", false)
      getData("play", false)
    } else
      getData(
        intent.getStringExtra("type") ?: "",
        intent.getBooleanExtra("isNewRelease", false)
      )
  }

  private fun getData(type: String, isNewRelease: Boolean) {
    lifecycleScope.launch {
      mainViewModel.getItems(
        type, isNewRelease
      ).collect() {
        when (it) {
          is DataState -> {
            if (it.data.data != null && it.data.data != null) {
              binding.progressBar.visibility = View.GONE
              binding.listRv.visibility = View.VISIBLE
              val res = it.data.data

              res?.result?.data?.forEach {
                dataList.add(it!!)
              }
              binding.listRv.scrollToPosition(0)
              adapter.submitList(dataList)
            } else {
              binding.progressBar.visibility = View.GONE
            }
          }
          is ErrorState -> {

          }
          LoadingState -> {

          }
        }
      }
    }
  }

  fun setOnItemClickListener(listener: (ListItem) -> Unit) {
    this.itemClickListener = listener
  }

  override fun onClicked(id: String) {
    val intent = Intent(this, DetailsActivity::class.java)
    intent.putExtra("movieId", id)
    startActivity(intent)
  }

}
