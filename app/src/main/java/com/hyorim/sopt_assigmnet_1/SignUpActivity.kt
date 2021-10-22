package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySignUpBinding

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
                var intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", id)
                    .putExtra("pw", pw)
                setResult(RESULT_OK, intent)
                if (!isFinishing) finish()
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

    companion object {
        private const val Tag = "SignUpActivity :"
    }

}