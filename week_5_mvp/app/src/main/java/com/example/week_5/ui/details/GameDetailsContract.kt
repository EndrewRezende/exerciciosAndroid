package com.example.week_5.ui.details

import com.example.week_5.data.modal.Game

interface GameDetailsContract {

    interface View {
        fun showGameDetails(game: Game)
        fun toastFailure()
        fun toolbarButtons()
//        fun showReleaseNull()
//        fun showRelease(game: Game)
//        fun gameReleaseDate(game: Game)
    }

    interface Presenter{
        fun apiObserver(gameID: Int)
    }
}