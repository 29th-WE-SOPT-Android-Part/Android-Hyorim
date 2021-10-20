package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyorim.sopt_assigmnet_1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    var Tag = "SignUpActivity :"
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var name   : String
    private lateinit var id     : String
    private lateinit var pw     : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**Sign Up Button*/
        binding.signUpBtn.setOnClickListener {

            name    = binding.nameEditText.text.toString()
            id      = binding.IDEditText.text.toString()
            pw      = binding.PWEditText.text.toString()

            if (isInputComplete()){
                var intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                setResult(RESULT_OK, intent)
                if (!isFinishing) finish()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isInputComplete() : Boolean{

        var isNameNull  = name.isNullOrBlank()
        var isIdNull    = id.isNullOrBlank()
        var isPwNull    = pw.isNullOrBlank()

        Log.d(Tag, "isNameNull :$isNameNull") //string template function
        Log.d(Tag, "isIdNull   :$isIdNull")
        Log.d(Tag, "isPwNull   :$isPwNull")

        return !isNameNull && !isIdNull && !isPwNull
    }

}