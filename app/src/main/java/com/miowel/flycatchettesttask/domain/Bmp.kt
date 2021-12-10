package com.miowel.flycatchettesttask.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bmp(
    val animationId: Int,
    val animationImages: List<String>,
    val directionId: Any,
    val numOfFrames: Int
)