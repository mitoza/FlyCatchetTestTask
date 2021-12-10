package com.miowel.flycatchettesttask.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Activities (
    val Activities: List<Activity>,
    val id: Int,
    val numOfActivities: Int,
    val title: String,
    val version: Int
) {

    fun getActivityByName(name: String): Activity? {
        for(activity in Activities) {
            if (activity.name == name) return activity;
        }
        return null
    }

}