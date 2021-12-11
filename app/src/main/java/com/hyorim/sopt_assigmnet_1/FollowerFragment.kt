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
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        addDiver()
        initAdapter()

        return binding.root
    }

    private fun addDiver() {
        binding.rvFollower.addItemDecoration(
            DividerItemDecoration(binding.rvFollower.context, ClipDrawable.HORIZONTAL)
        )
    }

    private fun initAdapter() {

        followerAdapter = FollowerAdapter()
        _binding!!.rvFollower.adapter = followerAdapter
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData(MINION1, "김효림", "내이름"),
                FollowerData(MINION2, "김효람", "이렇게 불릴 때도 있음"),
                FollowerData(MINION3, "비버", "닮은 동물"),
                FollowerData(MINION1, "루피", "닮은 캐릭터(?)"),
                FollowerData(MINION2, "리사이클러뷰 테스트", "스크롤 확인용 데이터")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MINION1 =
            "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
        const val MINION2 =
            "https://mblogthumb-phinf.pstatic.net/20151026_41/ddazero_1445794370582RDrBz_JPEG/Stuart.jpg?type=w800"
        const val MINION3 =
            "https://mblogthumb-phinf.pstatic.net/20151026_132/ddazero_14457954455582SYuc_JPEG/Kevin_the_minions_2015.jpg?type=w2"
    }
}