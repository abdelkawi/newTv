package com.eurisko.alballam.tv.ui.details

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.Utils
import com.eurisko.alballam.tv.data.model.EpisodesItem
import com.eurisko.alballam.tv.ui.OnItemClicked

class EpisodesAdapter(val itemClicked: OnItemClicked) : ListAdapter<EpisodesItem, EpisodeViewHolder>(DiffCallBackEpisode) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
    return EpisodeViewHolder(view)
  }

  override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
    holder.bind(getItem(position))
    holder.view.setOnKeyListener { v, keyCode, event ->
      if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER && event.action == KeyEvent.ACTION_DOWN)
        itemClicked.onClicked(getItem(position).id ?: "")
      false
    }
  }

}

class EpisodeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  fun bind(episodesItem: EpisodesItem) {
    view.findViewById<TextView>(R.id.episodeNumber).text = episodesItem.episodeNumber.toString()
    view.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus)
        Utils.focusIn(v)
      else Utils.focusOut(v)
    }

  }

}

object DiffCallBackEpisode : DiffUtil.ItemCallback<EpisodesItem>() {
  override fun areItemsTheSame(oldItem: EpisodesItem, newItem: EpisodesItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: EpisodesItem, newItem: EpisodesItem): Boolean {
    return oldItem.id == newItem.id
  }

}