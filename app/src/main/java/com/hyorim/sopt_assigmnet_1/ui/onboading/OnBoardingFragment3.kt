package com.hyorim.sopt_assigmnet_1.ui.onboading

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.databinding.FragmentOnBoarding3Binding
import com.hyorim.sopt_assigmnet_1.ui.signin.SignInActivity


class OnBoardingFragment3 : Fragment() {
    private var _binding : FragmentOnBoarding3Binding? = null
    private val binding get() = _binding ?: error("Not Initialized")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)

        setImage()
        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.btnStart.setOnClickListener {
            // getContext() 보다 requireActivity() 권장
            val intent = Intent(requireActivity(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun setImage(){
        val photo = R.drawable.minion
        binding.onboardingImg = photo
    }

}