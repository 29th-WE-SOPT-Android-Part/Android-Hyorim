package com.hyorim.sopt_assigmnet_1.ui.home.image

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.hyorim.sopt_assigmnet_1.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding ?: error("Not Initialized")
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentImageBinding.inflate(layoutInflater, container, false)

        initClickListener()
        setImage()

        return binding.root
    }

    private fun initClickListener() =
        binding.btnAppend.setOnClickListener { openGallery() }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        resultLauncher.launch(intent)
    }

    private fun setImage() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {

                    val uri = result.data?.data
                    binding.imageFromGallery = uri

                }
            }
    }

    private fun loadImage(imageView : ImageView, uri : Uri?){
        Glide.with(imageView.context)
            .load(uri)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}