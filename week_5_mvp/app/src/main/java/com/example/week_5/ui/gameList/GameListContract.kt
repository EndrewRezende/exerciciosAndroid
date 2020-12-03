package com.example.week_5.ui.gameList

import com.example.week_5.data.modal.GameResults

interface GameListContract {

    interface View {
        fun showGameList(gameResults: GameResults)
        fun toastFailure()
        fun onClickItem()
    }
    interface Presenter {
        fun apiObserver(page: Int)
    }
}