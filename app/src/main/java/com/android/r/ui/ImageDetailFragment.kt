package com.android.r.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarDetailBinding
import com.android.r.databinding.FragmentImageDetailBinding
import com.android.r.model.CarImage
import com.android.r.model.Rent
import com.android.r.viewmodel.CarImageViewModel
import com.android.r.viewmodel.RentViewModel
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel


class ImageDetailFragment : BaseFragment<FragmentImageDetailBinding>(R.layout.fragment_image_detail) {

    // 제스처 이벤트 감지하는 변수
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        initAfterBinding()
    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {
        super.initAfterBinding()

        val imageInfo = arguments?.getString("image")

        Glide.with(context!!)
            .load(imageInfo)
            .into(binding.ivAftercheckCar)

        mScaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

    }

    private fun onTouchEvent(motionEvent: MotionEvent?): Boolean {

        // 제스처 이벤트를 처리하는 메소드를 호출
        mScaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    // 제스처 이벤트를 처리하는 클래스
    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {

            scaleFactor *= scaleGestureDetector.scaleFactor

            // 최소 0.5, 최대 2배
            scaleFactor = Math.max(0.5f, Math.min(scaleFactor, 3.0f))

            // 이미지에 적용
            binding.ivAftercheckCar.scaleX = scaleFactor
            binding.ivAftercheckCar.scaleY = scaleFactor
            return true
        }
    }

}