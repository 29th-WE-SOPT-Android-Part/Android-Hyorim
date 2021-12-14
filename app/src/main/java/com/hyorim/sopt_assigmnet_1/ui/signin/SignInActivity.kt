package com.hyorim.sopt_assigmnet_1.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.data.RequestLoginData
import com.hyorim.sopt_assigmnet_1.data.ResponseLoginData
import com.hyorim.sopt_assigmnet_1.util.ServiceCreator
import com.hyorim.sopt_assigmnet_1.util.ViewExt.shortToast
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySignInBinding
import com.hyorim.sopt_assigmnet_1.ui.signup.SignUpActivity
import com.hyorim.sopt_assigmnet_1.ui.home.HomeActivity
import com.hyorim.sopt_assigmnet_1.util.SOPTSharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isFromSignUp()
        initClickEvent()
        isAutoLogin()

    }


    private fun isFromSignUp(){
        /** SignUpActivity에서 넘어온 경우*/
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val idFromSignUp = it.data?.getStringExtra("id") ?: ""
                    val pwFromSignUp = it.data?.getStringExtra("pw") ?: ""
                    binding.IDEditText.setText(idFromSignUp)
                    binding.IDEditText.setText(pwFromSignUp)
                }
            }
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

        /** AutoLogin Button*/
        binding.clAutoLogin.setOnClickListener{
            val curr = binding.cbAutoLogin.isSelected
            binding.cbAutoLogin.isSelected = !curr
        }
    }

    private fun isAutoLogin(){
        if (SOPTSharedPreferences.getAutoLogin(this)){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
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
                    shortToast("${data?.name}님 반갑습니다")
                    SOPTSharedPreferences.setAutoLogin(this@SignInActivity, binding.cbAutoLogin.isSelected)
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                } else {
                    shortToast("로그인에 실패하셨습니다")
                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

    private fun isInputComplete(): Boolean {
        // ID, PW의 EditText의 null 여부 판단

        val id = binding.IDEditText.text
        val pw = binding.PWEditText.text

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