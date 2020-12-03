package com.example.week_5.ui.details


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week_5.R
import com.example.week_5.data.modal.PlatformCapsule
import kotlinx.android.synthetic.main.layout_platform_item.view.*

class PlatformListAdapter (private val gameList: MutableList<PlatformCapsule>): RecyclerView.Adapter<PlatformListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_platform_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentGame = gameList[position]
        holder.itemView.tvPlatfmormTittle.text = currentGame.platform.name
    }

    override fun getItemCount() = gameList.size
}