package com.miowel.flycatchettesttask.ui.fragment_activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miowel.flycatchettesttask.R
import kotlinx.android.synthetic.main.fragment_activity.*

/**
 * Created by Dmitry on 09.12.2021.
 */
class ActivityFragment : Fragment() {

    private lateinit var viewModel: ActivityFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, ActivityFragmentViewModel.Factory)
            .get(ActivityFragmentViewModel::class.java)
        viewModel.initActivities(requireContext(), "01")
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        ibActivityPrev.setOnClickListener(viewModel::onPrevClick)
        ibActivityNext.setOnClickListener(viewModel::onNextClick)
        viewModel.currentStep.observe(viewLifecycleOwner, this::showStep)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showStep(step: Int) {
        Log.d("ActivityFragment","Step: $step")
        ibActivityPrev.visibility = if (step == 0) View.INVISIBLE else View.VISIBLE
        ibActivityNext.visibility = if (step == viewModel.getStepSize() - 1) View.INVISIBLE else View.VISIBLE
        ivActivityContainer.setImageBitmap(viewModel.getImageByStep(requireContext(), step))
    }
}