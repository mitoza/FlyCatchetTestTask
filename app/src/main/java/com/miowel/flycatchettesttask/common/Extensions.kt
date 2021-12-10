package com.miowel.flycatchettesttask.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun Context.getActivityJson(): String =
    this.assets.open("Activities.json").bufferedReader().use{it.readText()}


fun Context.getBitmap(path: String): Bitmap =
    this.assets.open(path).use{ BitmapFactory.decodeStream(it) }
