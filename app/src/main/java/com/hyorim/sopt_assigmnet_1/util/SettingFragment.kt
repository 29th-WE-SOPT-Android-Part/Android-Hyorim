package com.hyorim.sopt_assigmnet_1.util

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.hyorim.sopt_assigmnet_1.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
    }
}