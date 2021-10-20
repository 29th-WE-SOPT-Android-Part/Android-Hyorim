package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.hyorim.sopt_assigmnet_1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity :"

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val introduce = Introduce(
            "김효림",
            23,
            "ENTJ",
            "반갑습니다",
            R.drawable.my_photo
        )

        binding.introduce = introduce   // xml 변수 = line 21

        initTransactionEvent()
        initClickEvent()

    }

    private fun initClickEvent() {
        binding.gitIcon.setOnClickListener {
            Log.d(tag, "Git Icon Clicked")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/KxxHyoRim")
            startActivity(intent)
        }
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, followerFragment)
            .commit()

        binding.followerBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, followerFragment)
                .commit()
        }

        binding.repositoryBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, repositoryFragment)
                .commit()
        }

    }

}