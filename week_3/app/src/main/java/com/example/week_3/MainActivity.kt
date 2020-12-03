package com.example.week_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val players = listOf(
            User("Endrew Silveira Rezende", 21),
            User("Gustavo de Almeida Moron", 9),
            User("Murilo da Silva Reigado", 64),
            User("Luis Miranhamen", 100),
            User("Jaque Willy Wonka", 95),
            User("Lorem Ipsum", 18),
            User("Carolyne Lira Carvalho", 84),
            User("Lohane Vekanandri Stephany Smith Bueno de Raio Laiser", 79),
            User("Jack Sparrow", 27),
            User("Arnaldo Schwarzenegger", 41),
            User("Lucas Skywalker", 69),
            User("Edna Mode Nadadecapa ", 47),
            User("Dave Ozob Passos", 36),
            User("Nicole Dias", 23),
            User("Gabriella Santana Simpson Valdemort Monsterhigh", 10),
            User("Bilbo Baggins", 4)
        )


        reclycler_view.apply {
            adapter = UsersAdapter(players)
        }
    }
}