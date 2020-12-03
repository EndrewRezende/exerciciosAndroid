package com.example.week_5.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.example.week_5.*
import com.example.week_5.api.Games
import com.example.week_5.api.GamesApi
import com.example.week_5.model.GamesListAdapter
import com.example.week_5.model.RecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.loadingBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val DATA_KEY = "dataKey"
const val GAME_TITTLE = "GameTittle"

class MainActivity : AppCompatActivity() {

    //    Global Variables

    private val retrofit = GamesApi.create()
    var globalToast: Toast? = null
    var gameListAdapter: GamesListAdapter? = null
    private var loading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvGamesList.itemAnimator = null

        loadingBar.visibility = View.VISIBLE

        getGameList(1)

        // EndlessScrollView

        val endlessScrollListener = object : RecyclerViewScrollListener() {
            override fun onLoadMore(page: Int) {
                if (!loading) {
                    getGameList(page)
                }
            }
        }
        rvGamesList.addOnScrollListener(endlessScrollListener)
    }

    fun onClickItem() {
        gameListAdapter?.onClickItem = { game ->
            val intent = Intent(this, GameDetailsActivity::class.java).apply {
                putExtra(DATA_KEY, game.id)
                putExtra(GAME_TITTLE, game.name)
            }
            startActivity(intent)
        }
    }

    // region Retrofit CALLBACK

    fun getGameList(page: Int) {
        val retrofit = retrofit
//        val service = retrofit.create(GamesApi::class.java)
        val gamesCall = retrofit.getGameList(page)
        loading = true
        gamesCall.enqueue(object : Callback<Games> {
            override fun onResponse(call: Call<Games>, response: Response<Games>) {
                loading = false
                val games = response.body()
                if (games != null) {
                    if (gameListAdapter == null) {
                        gameListAdapter =
                            GamesListAdapter(games.results)
                        rvGamesList.adapter = gameListAdapter
                        onClickItem()
                        rvGamesList.visibility = View.VISIBLE
                        loadingBar.visibility = View.GONE
                    } else {
                        gameListAdapter?.addItems(games.results)
                    }
                }
            }

            override fun onFailure(call: Call<Games>, t: Throwable) {
                loading = false
                globalToast = makeText(this@MainActivity, getString(R.string.error_message), Toast.LENGTH_SHORT)
                globalToast?.show()
            }
        })
    }
    // endregion Game
}