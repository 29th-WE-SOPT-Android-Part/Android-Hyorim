package com.hyorim.sopt_assigmnet_1

data class ResponseSignUpData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data,
) {
    data class Data(
        val id: Int,
        val name: String,
        val email: String,
    )
}
