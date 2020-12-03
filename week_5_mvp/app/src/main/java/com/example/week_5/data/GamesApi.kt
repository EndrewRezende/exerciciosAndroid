package com.example.week_5.data

import com.example.week_5.data.modal.Game
import com.example.week_5.data.modal.GameResults
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {

    @GET("games?page_size=20")
    fun getGameList(@Query("page") page: Int): Observable<GameResults>

    @GET("games/{game_id}")
    fun getGameDetails(@Path ("game_id")gameId : Int): Observable<Game>

    companion object {
        fun create(): GamesApi {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.rawg.io/api/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create<GamesApi>(GamesApi::class.java)
        }

    }
}
