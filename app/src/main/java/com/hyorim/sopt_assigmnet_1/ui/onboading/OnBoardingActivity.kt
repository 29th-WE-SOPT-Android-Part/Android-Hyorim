package com.hyorim.sopt_assigmnet_1.ui.onboading

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.databinding.ActivityOnboardingBinding
import com.hyorim.sopt_assigmnet_1.ui.signin.SignInActivity
import com.hyorim.sopt_assigmnet_1.util.SOPTSharedPreferences

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkOnBoarding()
    }

    private fun checkOnBoarding() {
        if (!SOPTSharedPreferences.isStartOnBoarding(this)){
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        SOPTSharedPreferences.setOnBoardingFinish(this)
    }
}