package br.com.madeinweb.academy.week_3.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.madeinweb.academy.week_3.R
import br.com.madeinweb.academy.week_3.model.Ranking
import kotlinx.android.synthetic.main.activity_ranking.*

/**
 * Created by Éverdes Soares on 10/03/2020.
 */

class RankingActivity : AppCompatActivity() {

    private val adapter = RankingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        configView()
    }

    private fun configView() {
        adapter.onItemClickListener = { ranking ->
            checkClassification(ranking)
        }

        val rankings = getRanking()
        adapter.addData(rankings)

        rvRanking.adapter = adapter
    }

    private fun checkClassification(ranking: Ranking) {
        val message = if (ranking.point > 49) {
            getString(R.string.qualified, ranking.name)
        } else {
            getString(R.string.disqualified, ranking.name)
        }

        showToast(message)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getRanking(): List<Ranking> {
        return mutableListOf(
            Ranking("Cristiano José Pinheiro Lima dos Santos", 17),
            Ranking("João José", 64),
            Ranking("Pedro Paulo", 89),
            Ranking("Letícia Silva", 42),
            Ranking("Patrícia Oliveira", 95),
            Ranking("Tiago Silva", 31),
            Ranking("Bianca Duarte", 76),
            Ranking("Rafael Sousa", 28),
            Ranking("Vitória Fraga", 9),
            Ranking("Joaquim Pereira", 53),
            Ranking("José Silveira", 64),
            Ranking("Priscila Rodrigues", 89),
            Ranking("Talita Coelho", 42),
            Ranking("Mario Jorge", 57),
            Ranking("Renan Costa", 79),
            Ranking("Juliana Parente", 80),
            Ranking("Lucas Gabriel", 16),
            Ranking("Maria Francisca", 99),
            Ranking("Márcio Silva", 71),
            Ranking("Pedro Sampaio", 47)
        )
    }
}