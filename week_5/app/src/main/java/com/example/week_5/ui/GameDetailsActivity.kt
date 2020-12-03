package com.example.week_5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.week_5.*
import com.example.week_5.api.Game
import com.example.week_5.api.GamesApi
import com.example.week_5.model.PlatformListAdapter
import kotlinx.android.synthetic.main.activity_game_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailsActivity : AppCompatActivity() {

    // Global Variables

    private val retrofit = GamesApi.create()
    var globalToast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        val gameID = intent.getIntExtra(DATA_KEY, 1)
        val gameName = intent.getStringExtra(GAME_TITTLE)
        tbGameDetailTittle.title = gameName
        loadingBar.visibility = View.VISIBLE

        // Toolbar back button

        toolbarButtons()

        // Retrofit CALLBACK

        getGameDetail(gameID)

    }
    private fun getGameDetail(gameID: Int){
        val callGameList = retrofit.getGameDetails(gameID)
        callGameList.enqueue(object: Callback<Game> {
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                val game = response.body()
                if (game != null) {
                    val platformListAdapter =
                        PlatformListAdapter(game.platforms)
                    rvHorizontalScrollView.adapter = platformListAdapter

                    // Game detail's image loading

                    Glide.with(ivGameImage)
                        .load(game.backgroundImage)
                        .apply(
                            RequestOptions.circleCropTransform().centerCrop().error(
                                R.drawable.ic_alert
                            )
                        )
                        .into(ivGameImage)

                    // Game detail's info assignments

                    tvGameDescription.text = game.descriptionRaw
                    ratingBar.rating = game.rating
                    val gameDate =  if (game.released.isNullOrEmpty()) {
                        getString(R.string.null_text)
                    } else {
                        game.released
                    }
                    tvReleasedOn.text = getString(R.string.released_date, gameDate)
                    nestedScrollView.visibility = View.VISIBLE
                    loadingBar.visibility = View.GONE
                } else {
                    toastFailure()
                }
            }
            override fun onFailure(call: Call<Game>, t: Throwable) {
                toastFailure()
            }
        } )
    }

    private fun toolbarButtons(){
        setSupportActionBar(tbGameDetailTittle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        tbGameDetailTittle.setNavigationOnClickListener {
            finish()
        }
    }
    fun toastFailure(){
        globalToast = Toast.makeText(this@GameDetailsActivity, getString(R.string.error_message), Toast.LENGTH_SHORT)
        globalToast?.show()
    }
}