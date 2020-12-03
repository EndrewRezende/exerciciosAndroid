package com.example.week_5.ui.details

import com.example.week_5.data.modal.Game
import com.example.week_5.data.GamesApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GameDetailsPresenter : GameDetailsContract.Presenter  {

    lateinit var view: GameDetailsContract.View
    private val retrofit = GamesApi.create()

    override fun apiObserver(gameID: Int) {
        retrofit.getGameDetails(gameID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Game> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(game: Game) {
                        view.showGameDetails(game)
                    }

                    override fun onError(e: Throwable) {
                        view.toastFailure()
                    }

                    override fun onComplete() {}
                })
    }

}