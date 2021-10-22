package com.hyorim.sopt_assigmnet_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyorim.sopt_assigmnet_1.databinding.FollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerData>()

    class FollowerViewHolder(private val binding : FollowerListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : FollowerData) {
            binding.nameTv.text = data.name
            binding.introductionTv.text = data.introduction
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = FollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])

    }

    override fun getItemCount(): Int = followerList.size
}