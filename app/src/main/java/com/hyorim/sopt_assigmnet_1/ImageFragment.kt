package com.hyorim.sopt_assigmnet_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyorim.sopt_assigmnet_1.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding : FragmentImageBinding? = null
    private val binding get() = _binding ?: error("Not Initialized")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentImageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}