package com.hyorim.sopt_assigmnet_1.ui.onboading

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.data.SOPTSharedPreferences
import com.hyorim.sopt_assigmnet_1.databinding.ActivityOnboardingBinding
import com.hyorim.sopt_assigmnet_1.ui.signin.SignInActivity

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarTitle()
        checkOnBoarding()
    }

    private fun setToolbarTitle() {
        val navHostController =
            supportFragmentManager.findFragmentById(R.id.container_onboading) as NavHostFragment
        navController = navHostController.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setSupportActionBar(binding.tbOnBoarding)
        setupActionBarWithNavController(navController, appBarConfiguration )
    }

    private fun checkOnBoarding() {
        if (!SOPTSharedPreferences.isStartOnBoarding(this)) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        SOPTSharedPreferences.setOnBoardingFinish(this)
    }
}