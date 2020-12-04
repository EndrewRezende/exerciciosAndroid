package com.example.week_5.ui.gameList

import com.example.week_5.data.modal.GameResults
import com.example.week_5.data.GamesApi
import com.example.week_5.data.modal.Game
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class GameListPresenter : GameListContract.Presenter {

    lateinit var view: GameListContract.View
    private val retrofit = GamesApi.create()
    private val games = mutableListOf<Game>()

    override fun apiObserver(page: Int) {
        retrofit.getGameList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<GameResults> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(gameResults: GameResults) {
                        val newGames = gameResults.results
                        if (games.isEmpty()) {
                            view.createAdapter(newGames)
                        } else {
                            view.addItems(newGames)
                        }
                        games.addAll(newGames)
                    }

                    override fun onError(e: Throwable) {
                        view.toastFailure()
                    }

                    override fun onComplete() {}
                })
    }

}