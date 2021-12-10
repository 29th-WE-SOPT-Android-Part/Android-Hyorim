package com.hyorim.sopt_assigmnet_1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SampleViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){

    //    val fragments : MutableList<Fragment> =mutableListOf<>()
    val fragments : MutableList<Fragment> = mutableListOf()


    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}