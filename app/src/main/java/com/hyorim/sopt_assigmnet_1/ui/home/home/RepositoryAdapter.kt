package com.hyorim.sopt_assigmnet_1.ui.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hyorim.sopt_assigmnet_1.R
import com.hyorim.sopt_assigmnet_1.data.RepositoryData
import com.hyorim.sopt_assigmnet_1.databinding.RepositoryListBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    val repositoryList = mutableListOf<RepositoryData>()

    class RepositoryViewHolder(private val binding: RepositoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepositoryData) {
            binding.repository = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding: RepositoryListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.repository_list,
                parent,
                false)

        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }

    override fun getItemCount(): Int = repositoryList.size
}