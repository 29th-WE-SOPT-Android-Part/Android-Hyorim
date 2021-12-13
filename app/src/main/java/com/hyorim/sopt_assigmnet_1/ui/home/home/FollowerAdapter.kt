package com.hyorim.sopt_assigmnet_1.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyorim.sopt_assigmnet_1.data.FollowerData
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.databinding.FollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerData>()

    class FollowerViewHolder(private val binding: FollowerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.follower = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding: FollowerListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.follower_list,
                parent,
                false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size
}