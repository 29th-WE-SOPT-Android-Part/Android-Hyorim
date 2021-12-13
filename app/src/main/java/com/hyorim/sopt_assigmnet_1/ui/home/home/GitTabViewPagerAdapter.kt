package com.hyorim.sopt_assigmnet_1.ui.home.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GitTabViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position : Int): Fragment = fragments[position]

}