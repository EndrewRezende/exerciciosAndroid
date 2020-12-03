package com.example.week_5.ui.details

import com.example.week_5.data.modal.Game

interface GameDetailsContract {

    interface View {
        fun showGameDetails(game: Game)
        fun toastFailure()
        fun toolbarButtons()
    }

    interface Presenter{
        fun apiObserver(gameID: Int)
    }
}