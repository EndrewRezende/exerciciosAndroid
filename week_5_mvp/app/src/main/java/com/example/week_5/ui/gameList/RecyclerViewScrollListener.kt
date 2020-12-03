package com.example.week_5.ui.gameList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {

    private var oldY = 0

    var currentPage = 1
    abstract fun onLoadMore(page: Int)

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
        if (layoutManager != null) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val isScrollingToEnd = dy < oldY
            oldY = dy

            if (isScrollingToEnd && visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                currentPage++
                onLoadMore(currentPage)
            }
        }
    }
}