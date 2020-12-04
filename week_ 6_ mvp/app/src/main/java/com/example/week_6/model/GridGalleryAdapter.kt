package com.example.week_6.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week_6.R
import kotlinx.android.synthetic.main.gallery_grid_item.view.*

class GridGalleryAdapter (private val gridImage: List<Any>) : RecyclerView.Adapter<GridGalleryAdapter.GridGalleryHolder>() {

    inner class GridGalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridGalleryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_grid_item, parent, false)
        return GridGalleryHolder(view)
    }

    override fun getItemCount() = gridImage.size

    override fun onBindViewHolder(holder: GridGalleryHolder, position: Int) {
        val currentImage = gridImage[position]
        if (currentImage is Int) {
            holder.itemView.ivGridGalleryImage.setImageResource(currentImage)
        } else if (currentImage is String) {
            Glide.with(holder.itemView.ivGridGalleryImage)
                .load(currentImage)
                .into(holder.itemView.ivGridGalleryImage)
        }
    }
}