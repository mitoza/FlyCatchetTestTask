package com.miowel.flycatchettesttask.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Audio(
    val stepAudioURL: String,
    val stepName: String
)