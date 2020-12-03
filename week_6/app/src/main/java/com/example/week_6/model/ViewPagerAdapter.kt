package com.example.week_6.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week_6.R
import kotlinx.android.synthetic.main.view_pager_recycler_view.view.*

class ViewPagerAdapter (private val galleryImage: List<Any>, private val context: Context) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    private var verticalAdapter = VerticalGalleryAdapter(galleryImage)
    private var horizontalAdapter = HorizontalGalleryAdapter(galleryImage)
    private var gridAdapter = GridGalleryAdapter(galleryImage)

    private val tabItems = listOf(verticalAdapter, horizontalAdapter, gridAdapter)

    private val verticalLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    private val horizontalLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val gridLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
    private val layoutList = listOf(verticalLayoutManager, horizontalLayoutManager, gridLayoutManager)

    inner class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager_recycler_view, parent, false)
        return ViewPagerHolder(view)
    }

    override fun getItemCount() = tabItems.size

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val currentItem = tabItems[position]
        val currentLayout = layoutList[position]

        holder.itemView.rvVerticalGallery.layoutManager = currentLayout
        holder.itemView.rvVerticalGallery.adapter = currentItem

    }
}