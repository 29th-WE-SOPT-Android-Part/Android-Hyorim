package com.hyorim.sopt_assigmnet_1.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.databinding.ActivityHomeBinding
import com.hyorim.sopt_assigmnet_1.ui.home.home.HomeFragment
import com.hyorim.sopt_assigmnet_1.ui.home.image.ImageFragment
import com.hyorim.sopt_assigmnet_1.ui.home.profile.ProfileFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sampleViewPagerAdapter: SampleViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavigation()
    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), ImageFragment())

        sampleViewPagerAdapter = SampleViewPagerAdapter(this)
        sampleViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpSample.adapter = sampleViewPagerAdapter
    }

    private fun initBottomNavigation(){
        binding.vpSample.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvSample.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvSample.setOnItemSelectedListener{
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.vpSample.currentItem = PROFILE_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpSample.currentItem = HOME_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpSample.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object{
        const val PROFILE_FRAGMENT = 0
        const val HOME_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }

}