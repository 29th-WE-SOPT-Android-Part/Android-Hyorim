package com.hyorim.sopt_assigmnet_1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.hyorim.sopt_assigmnet_1.BindingConversions.loadImage
import com.hyorim.sopt_assigmnet_1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        val profile = ProfileData(
            "Kim Hyo Rim",
            "Kxxhyorim",
            "초보 개발자 김효림",
            R.drawable.my_photo     //R.id.~~ 하면 안됨
        )
        binding.profile = profile   // xml 변수 = line 21

        initTransactionEvent()
        gitClickEvent()
        binding.followerBtn.isSelected = true
    }

    private fun gitClickEvent() {
        binding.profilePhoto.setOnClickListener {
            Log.d(tag, "Git Icon Clicked")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/KxxHyoRim")
            startActivity(intent)
        }
    }

    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        // Start with default fragment (FollowerFragment)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, followerFragment)
            .commit()

        // when click event happens
        setFollowerFragment(followerFragment)
        setRepositoryFragment(repositoryFragment)
    }

    private fun setFollowerFragment(followerFragment : Fragment) {
        binding.followerBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, followerFragment)
            transaction.commit()

            // control btn color
            binding.followerBtn.isSelected = true
            binding.repositoryBtn.isSelected = false
        }
    }

    private fun setRepositoryFragment(repositoryFragment: Fragment) {
        binding.repositoryBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, repositoryFragment)
            transaction.commit()

            // control btn color
            binding.followerBtn.isSelected = false
            binding.repositoryBtn.isSelected = true
        }
    }

    companion object {
        const val tag = "HomeActivity :"
    }

}