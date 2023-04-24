package com.eurisko.alballam.tv.ui.loading

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eurisko.alballam.tv.R

class LoadingAdapter : RecyclerView.Adapter<ItemLoadingHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemLoadingHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
    return ItemLoadingHolder(view)
  }

  override fun getItemCount(): Int {
    return 5
  }

  override fun onBindViewHolder(holder: ItemLoadingHolder, position: Int) {
  }
}

class ItemLoadingHolder(val view: View) : RecyclerView.ViewHolder(view) {

}