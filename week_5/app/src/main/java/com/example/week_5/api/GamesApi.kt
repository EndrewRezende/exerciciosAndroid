package com.example.week_5.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {

    @GET("games?page_size=20")
    fun getGameList(@Query("page") page: Int): Call<Games>

    @GET("games/{game_id}")
    fun getGameDetails(@Path ("game_id")gameId : Int): Call<Game>

    companion object {
        fun create(): GamesApi {

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(GamesApi::class.java)
        }
    }

}
