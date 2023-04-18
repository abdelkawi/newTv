package com.eurisko.alballam.tv.ui.mainscreen

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.eurisko.alballam.tv.DataModel
import com.eurisko.alballam.tv.R
import com.eurisko.alballam.tv.data.model.MovieDetailBean

class ListFragment : RowsSupportFragment() {

  private var itemSelectedListener: ((MovieDetailBean) -> Unit)? = null
  private var itemClickListener: ((MovieDetailBean) -> Unit)? = null


  private var rootAdapter: ArrayObjectAdapter =
    ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM))

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    adapter = rootAdapter

    onItemViewSelectedListener = ItemViewSelectedListener()
    onItemViewClickedListener = ItemViewClickListener()
  }

  fun bindData(dataList: DataModel) {
    val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
    val headerItem = HeaderItem(getString(R.string.shows))
    dataList.homeDashboardBean.data?.result?.showsList?.forEach { item ->
      arrayObjectAdapter.add(item)
    }
    val listRow = ListRow(headerItem, arrayObjectAdapter)
    rootAdapter.add(listRow)

    val arrayObjectAdapter2 = ArrayObjectAdapter(ItemPresenter())
    val headerItem2 = HeaderItem(getString(R.string.plays))
    dataList.homeDashboardBean.data?.result?.playsList?.forEach { item ->
      arrayObjectAdapter2.add(item)
    }
    val listRow2 = ListRow(headerItem2, arrayObjectAdapter2)
    rootAdapter.add(listRow2)

  }

  fun setOnContentSelectedListener(listener: (MovieDetailBean) -> Unit) {
    this.itemSelectedListener = listener
  }

  fun setOnItemClickListener(listener: (MovieDetailBean) -> Unit) {
    this.itemClickListener = listener
  }

  inner class ItemViewSelectedListener : OnItemViewSelectedListener {
    override fun onItemSelected(
      itemViewHolder: Presenter.ViewHolder?,
      item: Any?,
      rowViewHolder: RowPresenter.ViewHolder?,
      row: Row?
    ) {
      if (item is MovieDetailBean) {
        itemSelectedListener?.invoke(item)
      }

    }
  }

  inner class ItemViewClickListener : OnItemViewClickedListener {
    override fun onItemClicked(
      itemViewHolder: Presenter.ViewHolder?,
      item: Any?,
      rowViewHolder: RowPresenter.ViewHolder?,
      row: Row?
    ) {
      if (item is MovieDetailBean) {
        itemClickListener?.invoke(item)
      }
    }

  }

}