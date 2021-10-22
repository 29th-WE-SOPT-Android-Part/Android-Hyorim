package com.hyorim.sopt_assigmnet_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyorim.sopt_assigmnet_1.databinding.FragmentRepositoryBinding


class RepositoryFragment : Fragment() {

    private var _binding : FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}