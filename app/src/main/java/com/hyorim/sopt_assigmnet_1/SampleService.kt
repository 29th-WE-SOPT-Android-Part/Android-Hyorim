package com.hyorim.sopt_assigmnet_1

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
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