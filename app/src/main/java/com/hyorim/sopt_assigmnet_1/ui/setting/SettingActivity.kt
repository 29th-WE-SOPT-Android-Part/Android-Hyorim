package com.hyorim.sopt_assigmnet_1.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.data.SOPTSharedPreferences
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySettingBinding
import com.hyorim.sopt_assigmnet_1.util.ViewExt.shortToast

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSwitch()
        initClickListener()
    }

    private fun initClickListener() {
        binding.swAutoLogin.setOnClickListener {
            val state = binding.swAutoLogin.isChecked
            if (!state){    // turn off autoLogin
                SOPTSharedPreferences.removeAutoLogin(this)
                Log.d("SettingActivity", "remove")
            } else {
                binding.swAutoLogin.isChecked = false
                shortToast("로그인 화면에서 설정해주세요")
            }
        }
    }

    private fun initSwitch() {
        binding.swAutoLogin.isChecked = SOPTSharedPreferences.getAutoLogin(this)
    }


}