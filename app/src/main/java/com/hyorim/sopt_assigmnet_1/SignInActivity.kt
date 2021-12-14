package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var idEditText: EditText
    private lateinit var pwEditText: EditText
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idEditText = binding.IDEditText
        pwEditText = binding.PWEditText

        /** SignUpActivity에서 넘어온 경우*/
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val idFromSignUp = it.data?.getStringExtra("id") ?: ""
                    val pwFromSignUp = it.data?.getStringExtra("pw") ?: ""
                    idEditText.setText(idFromSignUp)
                    pwEditText.setText(pwFromSignUp)
                }
            }

        initClickEvent()

    }

    private fun initClickEvent() {
        /** Login Button*/
        binding.loginBtn.setOnClickListener {
            if (isInputComplete()) {
                initNetwork()
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

        /** SignUp Button */
        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
            //startActivity(intent)
        }
    }

    private fun initNetwork() {
        val requestLoginData = RequestLoginData(
            binding.IDEditText.text.toString(),
            binding.PWEditText.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    Toast.makeText(this@SignInActivity, "${data?.name}님 반갑습니다", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
//                    finish()    //?
                } else {
                    Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

    private fun isInputComplete(): Boolean {
        // ID, PW의 EditText의 null 여부 판단

        val id = idEditText.text
        val pw = pwEditText.text

        val isIdNull = id.isNullOrBlank()
        val isPwNull = pw.isNullOrBlank()

        Log.d(tag, "isIdNull =$isIdNull")
        Log.d(tag, "isPwNull =$isPwNull")

        return !isIdNull && !isPwNull
    }

    companion object {
        const val tag = "SignInActivity :"
    }
}