package com.example.week_5.ui.gameList

import com.example.week_5.data.modal.Game
import com.example.week_5.data.modal.GameResults

interface GameListContract {

    interface View {
        fun toastFailure()
        fun onClickItem()

        fun createAdapter(results: List<Game>)
        fun addItems(results: List<Game>)
    }
    interface Presenter {
        fun apiObserver(page: Int)
    }
}