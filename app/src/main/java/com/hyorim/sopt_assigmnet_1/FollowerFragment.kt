package com.hyorim.sopt_assigmnet_1

import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.hyorim.sopt_assigmnet_1.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("Binding 이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        addDiver()
        initAdapter()

        return binding.root
    }
    private fun addDiver(){
        binding.rvFollower.addItemDecoration(
            DividerItemDecoration(binding.rvFollower.context, ClipDrawable.HORIZONTAL)
        )
    }

    private fun initAdapter() {

        followerAdapter = FollowerAdapter()

        _binding!!.rvFollower.adapter = followerAdapter

        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(R.drawable.minion,"김효림", "내이름"),
                FollowerData(R.drawable.minion,"김효람", "이렇게 불릴 때도 있음"),
                FollowerData(R.drawable.minion,"비버", "닮은 동물"),
                FollowerData(R.drawable.minion,"루피", "닮은 캐릭터(?)")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}