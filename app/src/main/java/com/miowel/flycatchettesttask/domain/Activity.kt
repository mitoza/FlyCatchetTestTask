package com.miowel.flycatchettesttask.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Activity(
    val id: Int,
    val name: String,
    val numOfSteps: Int,
    val stepImages: List<String>,
    //val animations: Animations,
    val title: String,
    val difficulty: Int,
    val type: Int,
    val audioList: List<Audio>
) {

    fun getImagePathByStep(step: Int): String {
        return "$name/" + stepImages[step];
    }

}