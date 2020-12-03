package com.example.week_5.ui.gameList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.week_5.R
import com.example.week_5.data.modal.Game

class GamesListAdapter(val gameList: MutableList<Game>) : RecyclerView.Adapter<GamesListAdapter.ViewHolder>() {

    var onClickItem: ((game: Game) -> Unit)? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Game card VIEWs assignments

        val gameTittle: TextView = itemView.findViewById(R.id.tvGameTittle)
        val releaseDate: TextView = itemView.findViewById(R.id.tvReleasedOn)
        val ratingStar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                val gamePosition = gameList[adapterPosition]
                onClickItem?.invoke(gamePosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_game_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nullText: String = holder.itemView.context.getString(R.string.null_text)
        val currentGame = gameList[position]

        // Game detail's image loading

        Glide.with(holder.imageView)
//              .load("")
            .load(currentGame.backgroundImage)
            .apply(
                RequestOptions.circleCropTransform().centerCrop().error(R.drawable.ic_alert)
            )
            .into(holder.imageView)

        // Game detail's info assignments

        holder.gameTittle.text = currentGame.name
        holder.ratingStar.rating = currentGame.rating

        val gameDate =  if (currentGame.released.isNullOrEmpty()) {
            nullText
        } else {
            currentGame.released
        }
        holder.releaseDate.text = holder.releaseDate.context.getString(R.string.released_date, gameDate)
    }

    override fun getItemCount() = gameList.size

    fun addItems(games: List<Game>) {
        val lastIndex = gameList.lastIndex
        gameList.addAll(games)
        notifyItemRangeInserted(lastIndex, games.size)
    }
}