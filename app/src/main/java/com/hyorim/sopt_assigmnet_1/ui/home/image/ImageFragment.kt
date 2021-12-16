package com.hyorim.sopt_assigmnet_1.ui.home.image

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.hyorim.sopt_assigmnet_1.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding ?: error("Not Initialized")
//    private var resultLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val uri = result.data?.data
//                binding.imageFromGallery = uri
//            }
//        }

    private val resultLauncher
      = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        binding.imageFromGallery = uri
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentImageBinding.inflate(layoutInflater, container, false)

        initClickListener()

        return binding.root
    }

    private fun initClickListener() =
        binding.btnAppend.setOnClickListener { openGallery() }

    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        resultLauncher.launch("image/*")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}