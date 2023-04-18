package com.eurisko.alballam.tv.ui.mainscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.R.id
import com.eurisko.alballam.tv.data.model.MovieDetailBean

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {

        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)

    }

    fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    fun getHeightInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.heightPixels ?: 0
        return (width * percent) / 100
    }


    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

        val content = item as? MovieDetailBean

        val imageview = viewHolder?.view?.findViewById<ImageView>(id.poster_image)

        val url = content?.image
        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageview!!)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}