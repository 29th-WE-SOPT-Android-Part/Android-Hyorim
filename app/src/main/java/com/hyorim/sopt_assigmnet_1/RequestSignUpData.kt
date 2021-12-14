package com.hyorim.sopt_assigmnet_1

import com.google.gson.annotations.SerializedName

data class RequestSignUpData(
    @SerializedName("email")
    val email : String,
    val name : String,
    val password : String
)
