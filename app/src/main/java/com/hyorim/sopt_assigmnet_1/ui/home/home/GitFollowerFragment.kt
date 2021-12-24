package com.hyorim.sopt_assigmnet_1.ui.home.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyorim.sopt_assigmnet_1.databinding.FragmentGitFollowerBinding

class GitFollowerFragment : Fragment() {

    private var _binding : FragmentGitFollowerBinding? = null
    private val binding get() = _binding ?: error("Not Initialized")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGitFollowerBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}