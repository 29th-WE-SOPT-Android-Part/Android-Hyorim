package com.hyorim.sopt_assigmnet_1.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import java.security.AccessController.getContext

object SOPTSharedPreferences {

    private const val STORAGE_KEY = "com.hyorim.sopt_assigmnet_1.USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val ON_BOARDING = "ON_BOARDING"

    private fun getPreferences(context: Context) =
        context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)

    fun getAutoLogin(context: Context): Boolean {
        val preferences = getPreferences(context)
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        val preferences = getPreferences(context)
        preferences.edit().putBoolean(AUTO_LOGIN, value).apply()
    }

    fun isStartOnBoarding(context: Context): Boolean {
        val preferences = getPreferences(context)
        return preferences.getBoolean(ON_BOARDING, true)
    }

    fun setOnBoardingFinish(context: Context) {
        val preferences = getPreferences(context)
        preferences.edit().putBoolean(ON_BOARDING, false).apply()
    }


    fun removeAutoLogin(context: Context) {
        val preferences = getPreferences(context)
        preferences.edit().remove(AUTO_LOGIN).apply()
    }

    fun clearStorage(context: Context) {
        val preferences = getPreferences(context)
        preferences.edit().clear().apply()
    }
}