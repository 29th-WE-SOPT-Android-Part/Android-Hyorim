package com.hyorim.sopt_assigmnet_1

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.hyorim.sopt_assigmnet_1.databinding.FragmentRepositoryBinding


class RepositoryFragment : Fragment() {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private var _binding: FragmentRepositoryBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)

        initAdapter()
        addDiver()

        return binding.root
    }
    private fun addDiver(){
        binding.rvRepository.addItemDecoration(
            DividerItemDecoration(binding.rvRepository.context, ClipDrawable.HORIZONTAL)
        )
    }
    private fun initAdapter() {
        repositoryAdapter = RepositoryAdapter()

        binding.rvRepository.adapter = repositoryAdapter

        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("안드로이드 리파지토리", "과제를 미리미리 합시다"),
                RepositoryData("서버 리파지토리", "설명 길게길게길게게길게길게길게길게"),
                RepositoryData("코틀린 스터디", "매주 목요일마다 Readme 정리"),
                RepositoryData("Univ Planner", "어쩌다보니 앱 출시까지 했어요"),
                RepositoryData("한우수급 예측 모델", "인공지능 미워"),
                RepositoryData("Data Minding", "곧 중간고사인데 언제 공부할까"),
                RepositoryData("GridLayout", "연습 중 입니다"),
                RepositoryData("이게 잘 들어가면", "Recyclerview 가 잘 작동하는것"),
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}