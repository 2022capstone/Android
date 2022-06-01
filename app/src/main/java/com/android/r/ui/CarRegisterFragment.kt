package com.android.r.ui

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toFile
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarRegisterBinding
import com.android.r.model.Car
import com.android.r.model.CarInfo
import com.android.r.viewmodel.CarViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.io.File
import java.net.URLEncoder

class CarRegisterFragment : BaseFragment<FragmentCarRegisterBinding>(R.layout.fragment_car_register) {

    private val carViewModel : CarViewModel by viewModel()
    var REQUEST_IMAGE_CODE = 0
    var REQUEST_CODE = 0

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

        binding.btnImageRegister.setOnClickListener {
            REQUEST_IMAGE_CODE = 1
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            startActivityForResult(intent, REQUEST_CODE)
        }

        binding.btnCarRegister.setOnClickListener {

            carViewModel.insertCarInfo(
                CarInfo(
                    binding.etRegisterNum.text.toString(),
                    binding.etRegisterModel.text.toString(),
                    binding.etRegisterLocation.text.toString(),
                    binding.etRegisterSeater.text.toString(),
                    URLEncoder.encode(carViewModel.encodeImageToBase64(binding.ivRegisterCar.drawable.toBitmap(), binding.ivRegisterCar.tag.toString().substring(
                        binding.ivRegisterCar.tag.toString().indexOf(".") + 1
                    )), "UTF-8"),
                    LocalDateTime.parse(binding.etRegisterStarttime.text.toString(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                    LocalDateTime.parse(binding.etRegisterEndtime.text.toString(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                    "y",
                    "nyh710"
                )
            )
            navController.navigate(R.id.action_carRegisterFragment_to_myCarFragment)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var selectedImageUri: Uri = data?.data!!

        Log.d("filetypee", selectedImageUri.lastPathSegment.toString())

        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor = context?.contentResolver?.query(selectedImageUri, proj, null, null, null)!!
        var index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c.moveToFirst()
        var result = c.getString(index)

        binding.ivRegisterCar.setImageURI(selectedImageUri)
        binding.ivRegisterCar.setTag(result)

    }

}