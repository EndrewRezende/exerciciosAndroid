package com.example.week_5.data.modal

import com.squareup.moshi.Json

class Game (
        var name: String,
        var id: Int,
        var platforms: MutableList<PlatformCapsule>,
        var released: String? = null,
        @Json(name = "background_image") var backgroundImage: String,
        var rating: Float,
        @Json(name = "description_raw") var descriptionRaw: String? = null
)
