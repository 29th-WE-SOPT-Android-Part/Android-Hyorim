package com.hyorim.sopt_assigmnet_1

import android.content.Context

object SOPTSharedPreferences {

    private const val USER_AUTH = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"

    fun getAutoLogin(context : Context) {
        val preferences = context.getSharedPreferences(USER_AUTH, Context.MODE_PRIVATE)

        preferences.getBoolean(AUTO_LOGIN, false)
    }
//    /*
//    fun setAutoLong(context: Context.
//        valprefenes
//    }*/
}