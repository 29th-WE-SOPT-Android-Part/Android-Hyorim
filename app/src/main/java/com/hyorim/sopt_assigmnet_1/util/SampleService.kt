package com.hyorim.sopt_assigmnet_1.util

import com.hyorim.sopt_assigmnet_1.data.RequestLoginData
import com.hyorim.sopt_assigmnet_1.data.RequestSignUpData
import com.hyorim.sopt_assigmnet_1.data.ResponseLoginData
import com.hyorim.sopt_assigmnet_1.data.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SampleService {
    @POST("user/login")
    fun postLogin(
        @Body requestLoginData: RequestLoginData
    ): Call<ResponseLoginData>

    @POST("user/signup")
    fun postSignUp(
        @Body requestSignUpData: RequestSignUpData
    ): Call<ResponseSignUpData>
}