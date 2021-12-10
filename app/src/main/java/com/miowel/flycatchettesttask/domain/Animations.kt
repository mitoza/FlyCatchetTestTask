package com.miowel.flycatchettesttask.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Animations(
    @Json(name = "13.bmp") val bmp: Bmp
)