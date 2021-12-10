package com.miowel.flycatchettesttask.ui.fragment_activity

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miowel.flycatchettesttask.common.getActivityJson
import com.miowel.flycatchettesttask.common.getBitmap
import com.miowel.flycatchettesttask.domain.Activities
import com.miowel.flycatchettesttask.domain.Activity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.FileNotFoundException

/**
 * Created by Dmitry on 09.12.2021.
 */
class ActivityFragmentViewModel : ViewModel() {

    companion object Factory : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ActivityFragmentViewModel() as T
        }
    }

    private val TAG: String = ActivityFragmentViewModel::class.java.simpleName

    val currentStep = MutableLiveData(0)

    private lateinit var activity: Activity

    fun initActivities(context: Context, name: String) {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<Activities> = moshi.adapter(Activities::class.java)
        val json = context.getActivityJson()
        val activities = adapter.fromJson(json)
        val activity = activities?.getActivityByName(name)
        if (activity != null) this.activity = activity
        else onError(NoSuchFieldError("Activity $name not found"))
        Log.d(TAG, "Activity: $activity")
    }

    fun onPrevClick(v: View) {
        var step: Int = currentStep.value ?: 0
        step--
        if (step < 0) step = 0
        currentStep.value = step
    }

    fun onNextClick(v: View) {
        val stepsSize = getStepSize()
        var step: Int = currentStep.value ?: stepsSize - 1
        step++
        if (step >= stepsSize) step = stepsSize - 1
        currentStep.value = step
    }

    private fun onError(error: Throwable) {
        Log.e(TAG, "Error: " + error.message)
    }

    /* Get methods implementation */

    fun getStepSize(): Int {
        if (!this::activity.isInitialized) return 0
        return activity.stepImages.size
    }

    fun getImageByStep(context: Context, step: Int): Bitmap? {
        if (!this::activity.isInitialized) return null
        val path = activity.getImagePathByStep(step)
        Log.d(TAG, "Image Path: $path")
        return try {
            context.getBitmap(path)
        } catch (e: FileNotFoundException) {
            null
        }
    }

}