package br.com.madeinweb.academy.week_3.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.madeinweb.academy.week_3.R
import br.com.madeinweb.academy.week_3.model.Ranking
import kotlinx.android.synthetic.main.activity_ranking_item.view.*

/**
 * Created by Éverdes Soares on 10/03/2020.
 */

class RankingAdapter : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    var rankings = mutableListOf<Ranking>()
    var onItemClickListener: ((ranking: Ranking) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.activity_ranking_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ranking = rankings[position]
        holder.itemView.apply {
            txtRankingName?.text = ranking.name
            txtRankingPoint?.text = txtRankingPoint.context.getString(R.string.points, ranking.point)
        }
    }

    override fun getItemCount() = rankings.size

    fun addData(list: List<Ranking>) {
        rankings.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val ranking = rankings[adapterPosition]
                onItemClickListener?.invoke(ranking)
            }
        }
    }
}