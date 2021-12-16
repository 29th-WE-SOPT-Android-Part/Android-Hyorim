package com.hyorim.sopt_assigmnet_1.ui.home.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.data.ProfileData
import com.hyorim.sopt_assigmnet_1.databinding.FragmentProfileBinding
import com.hyorim.sopt_assigmnet_1.ui.setting.SettingActivity

class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("Not initialized")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        initProfileData()
        initTransactionEvent()
        gitClickEvent()
        initClickListener()


        return binding.root
    }

    private fun initProfileData(){
        val profile = ProfileData(
            "Kim Hyo Rim",
            "Kxxhyorim",
            "초보 개발자 김효림",
            R.drawable.my_photo     //R.id.~~ 하면 안됨
        )
        binding.profile = profile   // xml 변수 = line 21
    }

    private fun initClickListener() {
        binding.icSetting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }
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
        binding.followerBtn.isSelected = true           // 앱 첫 실행시 default -> follower

        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        // Start with default fragment (FollowerFragment)
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, followerFragment)
            .commit()

        // when click event happens
        setFollowerFragment(followerFragment)
        setRepositoryFragment(repositoryFragment)
    }

    private fun setFollowerFragment(followerFragment : Fragment) {
        binding.followerBtn.setOnClickListener {

            onTransaction(followerFragment)

            // control btn color
            binding.followerBtn.isSelected = true
            binding.repositoryBtn.isSelected = false
        }
    }

    private fun setRepositoryFragment(repositoryFragment: Fragment) {
        binding.repositoryBtn.setOnClickListener {

            onTransaction(repositoryFragment)

            // control btn color
            binding.followerBtn.isSelected = false
            binding.repositoryBtn.isSelected = true
        }
    }

    private fun onTransaction(targetFragment : Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, targetFragment)
        transaction.commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val tag = "HomeActivity :"
    }

}