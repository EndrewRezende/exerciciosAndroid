package com.example.week_5.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.week_5.*
import com.example.week_5.data.modal.Game
import com.example.week_5.ui.gameList.DATA_KEY
import com.example.week_5.ui.gameList.GAME_TITTLE
import kotlinx.android.synthetic.main.activity_game_details.*
import kotlinx.android.synthetic.main.activity_game_details.loadingBar

class GameDetailsActivity : AppCompatActivity(), GameDetailsContract.View {

    // Global Variables

    var globalToast: Toast? = null

    private val presenter = GameDetailsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        presenter.view = this

        val gameID = intent.getIntExtra(DATA_KEY, 1)
        val gameName = intent.getStringExtra(GAME_TITTLE)
        tbGameDetailTittle.title = gameName
        loadingBar.visibility = View.VISIBLE

        // Toolbar back button
        toolbarButtons()
        presenter.apiObserver(gameID)

    }

    override fun showGameDetails(game: Game) {
        val platformListAdapter = PlatformListAdapter(game.platforms)
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
        val gameDate = if (game.released.isNullOrEmpty()) {
            getString(R.string.null_text)
        } else {
            game.released
        }
        tvReleasedOn.text = getString(R.string.released_date, gameDate)
        nestedScrollView.visibility = View.VISIBLE
        loadingBar.visibility = View.GONE
    }

    override fun toolbarButtons() {
        setSupportActionBar(tbGameDetailTittle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        tbGameDetailTittle.setNavigationOnClickListener {
            finish()
        }
    }
    override fun toastFailure() {
        globalToast = Toast.makeText(this@GameDetailsActivity, getString(R.string.error_message), Toast.LENGTH_SHORT)
        globalToast?.show()
    }
}