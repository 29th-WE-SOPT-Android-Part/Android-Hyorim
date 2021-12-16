package com.hyorim.sopt_assigmnet_1.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySettingBinding
import com.hyorim.sopt_assigmnet_1.util.SettingFragment

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("SettingActivity", "onCreate")


        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_setting, SettingFragment())
            .commit()
    }

    

}