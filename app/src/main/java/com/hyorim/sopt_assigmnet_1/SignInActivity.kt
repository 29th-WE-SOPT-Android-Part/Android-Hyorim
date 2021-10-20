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

class SignInActivity : AppCompatActivity() {

    private val tag = "SignInActivity :"
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
                Toast.makeText(this, idEditText.text.toString() + "님 환영합니다", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
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
}