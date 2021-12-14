package com.hyorim.sopt_assigmnet_1.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.hyorim.sopt_assigmnet_1.util.ViewExt.shortToast

object SOPTSharedPreferences {

    private const val STORAGE_KEY = "com.hyorim.sopt_assigmnet_1.USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    private fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)

    fun getAutoLogin(context: Context): Boolean {
        val preferences = getPreferences(context)
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, value: Boolean) {
        Log.d("SharedPreferences", "setAutoLogin")
        val preferences = getPreferences(context)
        preferences.edit().putBoolean(AUTO_LOGIN, value).apply()
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