package com.eurisko.alballam.tv.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.Utils
import com.eurisko.alballam.tv.data.model.ActorBean

class ProductionAdapter : ListAdapter<ActorBean, ActorItemViewHolder>(DiffCallBackFav) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
    return ActorItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: ActorItemViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}

class ActorItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  fun bind(actorBean: ActorBean) {
    Glide.with(view.context)
      .load(actorBean.profile)
      .centerCrop()
      .into(view.findViewById(R.id.ivMoviePhoto))
    view.setOnFocusChangeListener { v, hasFocus ->
      if (hasFocus)
        Utils.focusIn(v)
      else Utils.focusOut(v)
    }
  }

}

object DiffCallBackFav : DiffUtil.ItemCallback<ActorBean>() {
  override fun areItemsTheSame(oldItem: ActorBean, newItem: ActorBean): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: ActorBean, newItem: ActorBean): Boolean {
    return oldItem.id == newItem.id
  }

}
