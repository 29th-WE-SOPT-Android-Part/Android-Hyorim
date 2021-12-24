package com.hyorim.sopt_assigmnet_1.ui.onboading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.databinding.FragmentOnBoarding1Binding


class OnBoardingFragment1 : Fragment() {

    private var _binding : FragmentOnBoarding1Binding? = null
    private val binding get() = _binding ?: error("Not Initialized")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOnBoarding1Binding.inflate(layoutInflater, container, false)

        binding.btnNext.setOnClickListener{
            findNavController().navigate(R.id.action_onBoardingFragment1_to_onBoardingFragment2)
        }
        return binding.root
    }

}