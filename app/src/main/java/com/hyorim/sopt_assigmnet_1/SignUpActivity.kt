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
    private lateinit var name : String
    private lateinit var id : String
    private lateinit var pw : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name    = binding.nameEditText.text.toString()
        id      = binding.IDEditText.text.toString()
        pw      = binding.PWEditText.text.toString()

        /**Sign Up Button*/
        binding.signUpBtn.setOnClickListener {

            // 모든 정보가 다 입력되어 있을 때
            if (inputComplete()){
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("pw", pw)
                setResult(RESULT_OK, intent)
//                finish()
                if (!isFinishing) finish()

            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun inputComplete() : Boolean{

        var isNameNull  = name.isNullOrBlank()
        var isIdNull    = id.isNullOrBlank()
        var isPwNull    = pw.isNullOrBlank()

        Log.e(Tag, "isNameNull :$isNameNull") //string template function
        Log.e(Tag, "isIdNull   :$isIdNull")
        Log.e(Tag, "isPwNull   :$isPwNull")

        var isComplete  = !isNameNull && !isIdNull && !isPwNull

        return isComplete
    }


}