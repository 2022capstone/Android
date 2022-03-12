package com.android.r.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentStart2Binding
import com.android.r.databinding.FragmentTakePicturesBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TakePicturesFragment : BaseFragment<FragmentTakePicturesBinding>(R.layout.fragment_take_pictures) {

    val REQUEST_IMAGE_CAPTURE = 1 //카메라 사진 촬영 요청코드
    var REQUEST = 0
    lateinit var curPhotoPaht: String //문자열형태 사진 경로 값

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setPermission()

        val binding = FragmentTakePicturesBinding.inflate(inflater, container, false)

        binding.btnTakeFinish.setOnClickListener {
            navController.navigate(R.id.action_takePicturesFragment_to_usageDetailFragment)
        }

        binding.btnTakepicF.setOnClickListener{
            REQUEST = 1
            takeCapture() //기본 카메라 앱을 실행하여 사진 촬영
        }
        binding.btnTakepicB.setOnClickListener {
            REQUEST = 2
            takeCapture()
        }
        binding.btnTakepicD.setOnClickListener {
            REQUEST = 3
            takeCapture()
        }
        binding.btnTakepicDback.setOnClickListener {
            REQUEST = 4
            takeCapture()
        }
        binding.btnTakepicP.setOnClickListener {
            REQUEST = 5
            takeCapture()
        }
        binding.btnTakepicPback.setOnClickListener {
            REQUEST = 6
            takeCapture()
        }

        return binding.root
    }

    //카메라 촬영
    private fun takeCapture() {
        //기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                }catch (ex: IOException){
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this.requireContext(),
                        "com.android.r.fileporvider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    //이미지파일 생성
    private fun createImageFile(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd").format(Date())
        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_",".jpg", storageDir)
            .apply { curPhotoPaht = absolutePath }
    }

    //권한설정
    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 위험권한들이 허용되었을 경우 이 곳을 수행함.
                Toast.makeText(requireContext(), "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { // 설정해놓은 위험권한들 중 거부를 한 경우 이 곳을 수행함.
                Toast.makeText(requireContext(), "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(requireContext())
            .setPermissionListener(permission)
            .setRationaleMessage("카메라를 사용하시려면 권한을 허용하세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정]->[권한] 항목에서 허용해주세요")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과 값
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && requestCode == Activity.RESULT_OK){
            //이미를 성공적으로 가져왔다면
            val bitmap: Bitmap
            val file = File(curPhotoPaht)
            if(Build.VERSION.SDK_INT < 28){
                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, Uri.fromFile(file))
                when(REQUEST){
                    1 -> binding.btnTakepicF.setImageBitmap(bitmap)
                    2 -> binding.btnTakepicB.setImageBitmap(bitmap)
                    3 -> binding.btnTakepicD.setImageBitmap(bitmap)
                    4 -> binding.btnTakepicDback.setImageBitmap(bitmap)
                    5 -> binding.btnTakepicP.setImageBitmap(bitmap)
                    6 -> binding.btnTakepicPback.setImageBitmap(bitmap)
                }
            }else { //안드로이드 9.0버전보다 높을 경우
                val decode = ImageDecoder.createSource(
                    requireActivity().contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                when(REQUEST){
                    1 -> binding.btnTakepicF.setImageBitmap(bitmap)
                    2 -> binding.btnTakepicB.setImageBitmap(bitmap)
                    3 -> binding.btnTakepicD.setImageBitmap(bitmap)
                    4 -> binding.btnTakepicDback.setImageBitmap(bitmap)
                    5 -> binding.btnTakepicP.setImageBitmap(bitmap)
                    6 -> binding.btnTakepicPback.setImageBitmap(bitmap)
                }
            }
            savePhoto(bitmap)
        }
    }

    //갤러리에 저장
    private fun savePhoto(bitmap: Bitmap) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/" //사진폴더로 저장하기 위한 경로 선언
        val timestamp: String = SimpleDateFormat("yyyyMMdd").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder = File(folderPath)
        if(!folder.isDirectory) { //현재 해당 경로에 폴더가 존재하지 않는다면
            folder.mkdirs() //make directory
        }
        //실제 저장
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(context, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }
}