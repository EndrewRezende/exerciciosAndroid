package com.example.week_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_card.view.*

class UsersAdapter(private val users: List<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var toastGlobal: Toast? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.tv_names
        val userPoints: TextView = itemView.tv_points
        init {
            itemView.setOnClickListener {
                toastGlobal?.cancel()
                val userPosition = users[adapterPosition]
                val classified: String = itemView.context.getString(R.string.classified, userPosition.name)
                val declassified: String = itemView.context.getString(R.string.declassified, userPosition.name)
                val validationText = if (userPosition.points >= 50){
                    classified
                }else {
                    declassified
                }
                toastGlobal = Toast.makeText(itemView.context, validationText, Toast.LENGTH_SHORT)
                toastGlobal?.show()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = users[position]
        val pointString: String = holder.itemView.context.getString(R.string.points, currentItem.points)

        holder.userName.text = currentItem.name
        holder.userPoints.text = pointString
    }

    override fun getItemCount() =  users.size
}