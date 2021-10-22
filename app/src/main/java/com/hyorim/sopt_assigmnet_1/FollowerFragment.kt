package com.hyorim.sopt_assigmnet_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyorim.sopt_assigmnet_1.databinding.FragmentFollowerBinding
import com.hyorim.sopt_assigmnet_1.databinding.FragmentRepositoryBinding


class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {

        followerAdapter = FollowerAdapter()

        _binding!!.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("김효림", "내이름"),
                FollowerData("김효람", "이렇게 불릴 때도 있음"),
                FollowerData("비버", "내 별명"),
                FollowerData("루피", "닮은 캐릭터(?)"),
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}