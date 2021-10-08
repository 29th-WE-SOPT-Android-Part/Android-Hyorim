package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    var Tag = "SignInActivity :"
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Login Button*/
        binding.loginBtn.setOnClickListener {

            // ID, PW의 EditText의 null 여부 판단
            var id = binding.IDEditText.text.toString()
            var pw = binding.PWEditText.text.toString()

            var isIdNull = id.isNullOrBlank()
            var isPwNull = pw.isNullOrBlank()

            Log.e(Tag,"isIdNull =" + isIdNull.toString())
            Log.e(Tag,"isPwNull =" + isPwNull.toString())

            if (!isIdNull && !isPwNull) {   // 둘 다 입력 된 경우
                Toast.makeText(this, id + "님 환영합니다", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }

        /** SignUp Button */
        binding.signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}