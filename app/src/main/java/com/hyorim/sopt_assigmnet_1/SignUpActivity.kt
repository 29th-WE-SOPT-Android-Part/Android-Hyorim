package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.content.pm.SigningInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySignUpBinding
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySigninBinding

class SignUpActivity : AppCompatActivity() {
    var Tag = "SignUpActivity :"
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**Sign Up Button*/
        binding.signUpBtn.setOnClickListener {

            var name    = binding.nameEditText.text.toString()
            var id      = binding.IDEditText.text.toString()
            var pw      = binding.PWEditText.text.toString()

            var isNameNull  = name.isNullOrBlank()
            var isIdNull    = id.isNullOrBlank()
            var isPwNull    = pw.isNullOrBlank()

            Log.e(Tag,"isNameNull   =" + isNameNull.toString())
            Log.e(Tag,"isIdNull     =" + isIdNull.toString())
            Log.e(Tag,"isPwNull     =" + isPwNull.toString())

            if (!isNameNull && !isIdNull && !isPwNull){
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }

        }
    }
}