package com.eurisko.alballam.tv.ui.listing

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.Utils
import com.eurisko.alballam.tv.data.model.ListItem
import com.eurisko.alballam.tv.ui.OnItemClicked

class ListingItemAdapter(val onItemClicked: OnItemClicked) : ListAdapter<ListItem, ListItemViewHolder>(DiffCallBackList) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_view, parent, false)
    return ListItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
    holder.bind(getItem(position))
    holder.view.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.action == KeyEvent.ACTION_DOWN)
        onItemClicked.onClicked(getItem(position).id ?: "")
      false
    }
  }
}

class ListItemViewHolder(val view: View) : ViewHolder(view) {
  fun bind(movie: ListItem) {
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

object DiffCallBackList : DiffUtil.ItemCallback<ListItem>() {
  override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
    return oldItem.id == newItem.id
  }

}