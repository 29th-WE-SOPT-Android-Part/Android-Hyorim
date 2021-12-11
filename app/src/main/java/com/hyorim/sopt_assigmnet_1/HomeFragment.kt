package com.hyorim.sopt_assigmnet_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.hyorim.sopt_assigmnet_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("Not Initialized")
    private lateinit var gitTabViewPagerAdapter: GitTabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(GitFollowingFragment(), GitFollowerFragment())

        gitTabViewPagerAdapter = GitTabViewPagerAdapter(this)
        gitTabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpFollow.adapter = gitTabViewPagerAdapter
    }


    private fun initTabLayout() {
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) {
            tab, position -> tab.text = tabLabel[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}