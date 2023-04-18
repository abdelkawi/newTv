package com.eurisko.alballam.tv.ui.search

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.Utils
import com.eurisko.alballam.tv.data.model.SearchItem
import com.eurisko.alballam.tv.ui.OnItemClicked

class SearchItemAdapter(val onItemClicked: OnItemClicked) : ListAdapter<SearchItem, SearchItemViewHolder>(DiffCallBackSearch) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_view, parent, false)
    return SearchItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
    holder.bind(getItem(position))
    holder.view.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.action == KeyEvent.ACTION_DOWN)
        onItemClicked.onClicked(getItem(position).id ?: "")
      false
    }
  }
}

class SearchItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  fun bind(movie: SearchItem) {
    Glide.with(view.context)
      .load(movie.image)
      .centerCrop()
      .into(view.findViewById(R.id.poster_image))
    view.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus)
        Utils.focusIn(v)
      else Utils.focusOut(v)
    }
  }

}

object DiffCallBackSearch : DiffUtil.ItemCallback<SearchItem>() {
  override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
    return oldItem.id == newItem.id
  }

}