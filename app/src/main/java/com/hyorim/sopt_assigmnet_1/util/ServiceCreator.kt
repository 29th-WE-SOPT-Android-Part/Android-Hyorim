package com.hyorim.sopt_assigmnet_1.util

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    var gson = GsonBuilder().setLenient().create()

    // 고정헤더 값에 사용한 Interceptor
    private val interceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson)) //gson converter 연동
        .build()

    val sampleService: SampleService = retrofit.create(SampleService :: class.java)

}