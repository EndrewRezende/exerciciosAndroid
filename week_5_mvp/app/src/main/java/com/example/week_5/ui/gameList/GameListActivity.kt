package com.example.week_5.ui.gameList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week_5.*
import com.example.week_5.data.modal.Game
import com.example.week_5.ui.details.GameDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.loadingBar

const val DATA_KEY = "dataKey"
const val GAME_TITTLE = "GameTittle"

class GameListActivity : AppCompatActivity(), GameListContract.View {

    //    Global Variables

    var globalToast: Toast? = null
    var gameListAdapter: GamesListAdapter? = null
    private var loading = false
    private val presenter = GameListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.view = this
        rvGamesList.itemAnimator = null
        loadingBar.visibility = View.VISIBLE

        presenter.apiObserver(1)
        endlessScrollListener()

    }

    private fun endlessScrollListener(){
        val endlessScrollListener = object : RecyclerViewScrollListener() {
            override fun onLoadMore(page: Int) {
                if (!loading) {
                    presenter.apiObserver(page)
                }
            }
        }
        rvGamesList.addOnScrollListener(endlessScrollListener)
    }

    override fun createAdapter(results: List<Game>) {
        gameListAdapter = GamesListAdapter(results.toMutableList())
        rvGamesList.adapter = gameListAdapter
        onClickItem()
        rvGamesList.visibility = View.VISIBLE
        loadingBar.visibility = View.GONE
    }

    override fun addItems(results: List<Game>) {
        gameListAdapter?.addItems(results)
    }

    override fun onClickItem() {
        gameListAdapter?.onClickItem = { game ->
            val intent = Intent(this, GameDetailsActivity::class.java).apply {
                putExtra(DATA_KEY, game.id)
                putExtra(GAME_TITTLE, game.name)
            }
            startActivity(intent)
        }
    }

    override fun toastFailure() {
        globalToast = Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT)
        globalToast?.show()
    }

}