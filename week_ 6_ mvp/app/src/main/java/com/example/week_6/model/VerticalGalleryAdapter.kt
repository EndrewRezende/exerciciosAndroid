package com.example.week_6.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week_6.R
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.android.synthetic.main.gallery_picture.view.*

class VerticalGalleryAdapter (private val verticalImage: List<Any>) : RecyclerView.Adapter<VerticalGalleryAdapter.VerticalGalleryHolder>(){

    inner class VerticalGalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalGalleryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_picture, parent, false)
        return VerticalGalleryHolder(view)
    }

    override fun getItemCount() = verticalImage.size

    override fun onBindViewHolder(holder: VerticalGalleryHolder, position: Int) {
        val currentImage = verticalImage[position]
        if (currentImage is Int) {
            holder.itemView.ivVerticalImage.setImageResource(currentImage)
        } else if (currentImage is String) {
            Glide.with(holder.itemView.ivVerticalImage)
                .load(currentImage)
                .into(holder.itemView.ivVerticalImage)
        }
    }
}