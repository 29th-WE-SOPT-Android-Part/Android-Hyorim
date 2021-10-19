package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.hyorim.sopt_assigmnet_1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    val tag = "HomeActivity :"
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

//        setContentView(binding.root)

        val introduce = Introduce (
            "김효림",
            23,
            "ENTJ",
            "반갑습니다"
        )

        binding.introduce = introduce   // xml 변수 = line 21


        binding.gitIcon.setOnClickListener{
            Log.i(tag, "Git Icon Clicked")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/KxxHyoRim")
            startActivity(intent)
        }
    }
}