package com.eurisko.alballam.tv.ui.fav

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
import com.eurisko.alballam.tv.data.model.Movie
import com.eurisko.alballam.tv.ui.OnItemClicked

class FavItemAdapter(val onItemClicked: OnItemClicked) : ListAdapter<Movie, FavItemViewHolder>(DiffCallBackFav) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_view, parent, false)
    return FavItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: FavItemViewHolder, position: Int) {
    holder.bind(getItem(position))
    holder.view.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.action == KeyEvent.ACTION_DOWN)
        onItemClicked.onClicked(getItem(position).id ?: "")
      false
    }
  }
}

class FavItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  fun bind(movie: Movie) {
    Glide.with(view.context)
      .load(movie.image)
      .centerCrop()
      .into(view.findViewById(R.id.ivMoviePhoto))
    view.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus)
        Utils.focusIn(v)
      else Utils.focusOut(v)
    }

  }

}

object DiffCallBackFav : DiffUtil.ItemCallback<Movie>() {
  override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem.id == newItem.id
  }

}