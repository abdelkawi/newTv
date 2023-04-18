package com.eurisko.alballam.tv.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eurisko.alballam.tv.State.DataState
import com.eurisko.alballam.tv.State.ErrorState
import com.eurisko.alballam.tv.State.LoadingState
import com.eurisko.alballam.tv.databinding.FragmentSearchBinding
import com.eurisko.alballam.tv.ui.MainViewModel
import com.eurisko.alballam.tv.ui.OnItemClicked
import com.eurisko.alballam.tv.ui.details.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchActivity : FragmentActivity(), OnItemClicked {
  lateinit var binding: FragmentSearchBinding
  private val viewModel: MainViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = FragmentSearchBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.searchView.requestFocus()
    binding.searchView.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      }

      override fun afterTextChanged(s: Editable?) {
        search(s.toString())
      }
    })
  }

  private fun search(txt: String) {


    lifecycleScope.launch {
      viewModel.search(txt).collect {
        when (it) {
          is DataState -> {
            if (it.data.body() != null && it.data.body()?.data != null) {
              binding.progressBar.visibility = View.GONE
              binding.emptySearchTv.visibility = View.GONE
              binding.searchHintTv.visibility = View.GONE
              binding.searchRv.visibility = View.VISIBLE
              val res = it.data.body()?.data
              val adapter = SearchItemAdapter(this@SearchActivity)
              adapter.submitList(res?.result?.data)
              binding.searchRv.layoutManager = GridLayoutManager(this@SearchActivity, 4)
              binding.searchRv.adapter = adapter
            } else {
              binding.progressBar.visibility = View.GONE
              binding.searchHintTv.visibility = View.GONE
              binding.emptySearchTv.visibility = View.VISIBLE
              binding.emptySearchTv.text = it.data.message()
              binding.searchRv.visibility = View.GONE
            }
          }
          is ErrorState -> {
            binding.progressBar.visibility = View.GONE
            binding.searchHintTv.visibility = View.GONE
            binding.emptySearchTv.visibility = View.VISIBLE
            binding.emptySearchTv.text = it.exception
            binding.searchRv.visibility = View.GONE
          }
          LoadingState -> {
            binding.progressBar.visibility = View.VISIBLE
            binding.searchHintTv.visibility = View.GONE
            binding.emptySearchTv.visibility = View.GONE
            binding.searchRv.visibility = View.GONE
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