package com.hyorim.sopt_assigmnet_1.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.data.RequestSignUpData
import com.hyorim.sopt_assigmnet_1.data.ResponseSignUpData
import com.hyorim.sopt_assigmnet_1.util.ServiceCreator
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySignUpBinding
import com.hyorim.sopt_assigmnet_1.ui.signin.SignInActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**Sign Up Button*/
        binding.signUpBtn.setOnClickListener {

            var name = binding.nameEditText.text.toString()
            var id = binding.IDEditText.text.toString()
            var pw = binding.PWEditText.text.toString()

            if (isInputComplete(name, id, pw)) {
                initNetwork()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isInputComplete(name: String, id: String, pw: String): Boolean {

        val isNameNull = name.isNullOrBlank()
        val isIdNull = id.isNullOrBlank()
        val isPwNull = pw.isNullOrBlank()

        Log.d(Tag, "isNameNull :$isNameNull") //string template function
        Log.d(Tag, "isIdNull   :$isIdNull")
        Log.d(Tag, "isPwNull   :$isPwNull")

        return !isNameNull && !isIdNull && !isPwNull
    }

    private fun initNetwork() {
        val name = binding.nameEditText.text.toString()
        val id = binding.IDEditText.text.toString()
        val pw = binding.PWEditText.text.toString()

        val requestSignUpData = RequestSignUpData(
            binding.IDEditText.text.toString(),
            binding.nameEditText.text.toString(),
            binding.PWEditText.text.toString()
        )

        val call: Call<ResponseSignUpData> =
            ServiceCreator.sampleService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Toast.makeText(this@SignUpActivity,
                        "${data?.name}님, 회원가입 성공",
                        Toast.LENGTH_SHORT).show()

                    // SignInActivity 로 이동
                    var intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    intent.putExtra("id", id)
                        .putExtra("pw", pw)
                    setResult(RESULT_OK, intent)
                    if (!isFinishing) finish()

                } else {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })

    }

    companion object {
        private const val Tag = "SignUpActivity :"
    }

}