package com.example.week_6.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week_6.R
import kotlinx.android.synthetic.main.gallery_horizontal_item.view.*

class HorizontalGalleryAdapter (private val horizontalImage: List<Any>) : RecyclerView.Adapter<HorizontalGalleryAdapter.HorizontalGalleryHolder>() {

    inner class HorizontalGalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalGalleryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_horizontal_item, parent, false)
        return HorizontalGalleryHolder(view)
    }

    override fun getItemCount() = horizontalImage.size

    override fun onBindViewHolder(holder: HorizontalGalleryHolder, position: Int) {
        val currentImage = horizontalImage[position]
        if (currentImage is Int) {
            holder.itemView.ivHorizontalGalleryImage.setImageResource(currentImage)
        } else if (currentImage is String) {
            Glide.with(holder.itemView.ivHorizontalGalleryImage)
                .load(currentImage)
                .into(holder.itemView.ivHorizontalGalleryImage)
        }
    }
}