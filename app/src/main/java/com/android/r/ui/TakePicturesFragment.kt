package com.android.r.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentTakePicturesBinding
import com.android.r.model.CarImage
import com.android.r.model.Rent
import com.android.r.model.RentInfo
import com.android.r.viewmodel.CarImageViewModel
import com.android.r.viewmodel.RentViewModel
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class TakePicturesFragment : BaseFragment<FragmentTakePicturesBinding>(R.layout.fragment_take_pictures) {

    var REQUEST_IMAGE_CAMERA = 1 //카메라 사진 촬영 요청코드
    var REQUEST_IMAGE_GALLERY = 7
    lateinit var imagePath : File
    lateinit var curPhotoPath: String //문자열형태 사진 경로 값
    val carImageViewModel : CarImageViewModel by viewModel()
    val rentViewModel : RentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()
    }

    override fun initAfterBinding() {


        setPermission()

        //촬영 완료 버튼
        binding.btnTakeFinish.setOnClickListener {

            val rentInfo = arguments?.getSerializable("rent") as Rent

            if(rentInfo.status == "2"){
                rentViewModel.updateRentInfo(
                    RentInfo(
                        rentInfo.rentId, rentInfo.renterId, rentInfo.carInfo.carNumber,
                        LocalDateTime.parse(rentInfo.startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                        LocalDateTime.parse(rentInfo.endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                        "3" , 0.0.toFloat(),  ""
                    )
                )

                carImageViewModel.insertCarImageBeforeRent(
                    CarImage(
                        rentInfo.rentId,
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivF.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivB.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivDriver.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivDriverB.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivPassenger.drawable.toBitmap(),"jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivPassengerB.drawable.toBitmap(),"jpg"), "UTF-8"),
                        "","","","","",""
                    )
                )
            }

            else if(rentInfo.status == "4"){
                /*rentViewModel.updateRentInfo(
                    RentInfo(
                        rentInfo.rentId, rentInfo.renterId, rentInfo.carInfo.carNumber,
                        LocalDateTime.parse(rentInfo.startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                        LocalDateTime.parse(rentInfo.endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                        "6" , 0.0.toFloat(),  ""
                    )
                )*/

                carImageViewModel.insertCarImageAfterRent(
                    CarImage(
                        rentInfo.rentId,
                        "","","","","","",
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivF.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivB.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivDriver.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivDriverB.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivPassenger.drawable.toBitmap(), "jpg"), "UTF-8"),
                        URLEncoder.encode(carImageViewModel.encodeImageToBase64(binding.ivPassengerB.drawable.toBitmap(), "jpg"), "UTF-8")
                    )
                )
            }


            navController.navigate(R.id.action_takePicturesFragment_to_usageDetailFragment)
        }


        binding.btnTakepicF.setOnClickListener{
            REQUEST_IMAGE_CAMERA = 1
            takeCapture() //기본 카메라 앱을 실행하여 사진 촬영
        }
        binding.btnTakepicB.setOnClickListener {
            REQUEST_IMAGE_CAMERA = 2
            takeCapture()
        }
        binding.btnTakepicD.setOnClickListener {
            REQUEST_IMAGE_CAMERA = 3
            takeCapture()
        }
        binding.btnTakepicDback.setOnClickListener {
            REQUEST_IMAGE_CAMERA = 4
            takeCapture()
        }
        binding.btnTakepicP.setOnClickListener {
            REQUEST_IMAGE_CAMERA = 5
            takeCapture()
        }
        binding.btnTakepicPback.setOnClickListener {
            REQUEST_IMAGE_CAMERA = 6
            takeCapture()
        }

        binding.btnGalleryF.setOnClickListener {
            REQUEST_IMAGE_GALLERY = 7
            loadGallery()
        }

        binding.btnGalleryB.setOnClickListener {
            REQUEST_IMAGE_GALLERY = 8
            loadGallery()
        }

        binding.btnGalleryD.setOnClickListener {
            REQUEST_IMAGE_GALLERY = 9
            loadGallery()
        }

        binding.btnGalleryDback.setOnClickListener {
            REQUEST_IMAGE_GALLERY = 10
            loadGallery()
        }

        binding.btnGalleryP.setOnClickListener {
            REQUEST_IMAGE_GALLERY = 11
            loadGallery()
        }

        binding.btnGalleryPback.setOnClickListener {
            REQUEST_IMAGE_GALLERY = 12
            loadGallery()
        }

        super.initAfterBinding()
    }

    //카메라 촬영
    private fun takeCapture() {
        //기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager).also {
                val photoFile: File? = try{
                    createImageFile()
                }catch(ex:IOException){
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this.requireContext(),
                        "com.android.r.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAMERA)
                }
            }
        }
    }

    //이미지파일 생성
    private fun createImageFile(): File {
        /*Log.d("test", "createImageFile")
        var file = File(Environment.getExternalStorageDirectory(), "/path/")
        if (!file.exists()) file.mkdir()

        var imageFile = File("${Environment.getExternalStorageDirectory().absoluteFile}/path/", "${fileName}")
        imagePath = imageFile.absoluteFile
        return imageFile*/

        //val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${REQUEST_IMAGE_CAMERA}", ".jpg", storageDir)
            .apply { curPhotoPath = absolutePath }

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

        if(requestCode == REQUEST_IMAGE_CAMERA && resultCode == Activity.RESULT_OK){
            //이미지를 성공적으로 가져왔다면
            val bitmap: Bitmap
            val file = File(curPhotoPath)

            if(Build.VERSION.SDK_INT < 28){
                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, Uri.fromFile(file))

                when(REQUEST_IMAGE_CAMERA){
                    1 ->  binding.ivF.setImageBitmap(bitmap)
                    2 ->  binding.ivB.setImageBitmap(bitmap)
                    3 ->  binding.ivDriver.setImageBitmap(bitmap)
                    4 ->  binding.ivDriverB.setImageBitmap(bitmap)
                    5 ->  binding.ivPassenger.setImageBitmap(bitmap)
                    6 ->  binding.ivPassengerB.setImageBitmap(bitmap)
                }
            }else { //안드로이드 9.0버전보다 높을 경우
                val decode = ImageDecoder.createSource(
                    this.requireActivity().contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)

                when(REQUEST_IMAGE_CAMERA){
                    1 ->  binding.ivF.setImageBitmap(bitmap)
                    2 ->  binding.ivB.setImageBitmap(bitmap)
                    3 ->  binding.ivDriver.setImageBitmap(bitmap)
                    4 ->  binding.ivDriverB.setImageBitmap(bitmap)
                    5 ->  binding.ivPassenger.setImageBitmap(bitmap)
                    6 ->  binding.ivPassengerB.setImageBitmap(bitmap)
                    7 -> binding.ivF.setImageBitmap(bitmap)
                }
            }


            //savePhoto(bitmap)
        }
        else if(requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK){
            var imageUriF: Uri = data?.data!!
            var imageUriB: Uri = data?.data!!
            var imageUriD: Uri = data?.data!!
            var imageUriDBack: Uri = data?.data!!
            var imageUriP: Uri = data?.data!!
            var imageUriPBack: Uri = data?.data!!

            fun getFileTag(imageUri: Uri) : String? {
                var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
                var c: Cursor = context?.contentResolver?.query(imageUri, proj, null, null, null)!!
                var index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                c.moveToFirst()
                var result = c.getString(index)

                return result
            }

            when(REQUEST_IMAGE_GALLERY){
                7 -> {
                    binding.ivF.setImageURI(imageUriF)
                    binding.ivF.setTag(getFileTag(imageUriF))}
                8 -> {
                    binding.ivB.setImageURI(imageUriB)
                    binding.ivB.setTag(getFileTag(imageUriB))
                }
                9 -> {
                    binding.ivDriver.setImageURI(imageUriD)
                    binding.ivDriver.setTag(getFileTag(imageUriD))
                }
                10 -> {
                    binding.ivDriverB.setImageURI(imageUriDBack)
                    binding.ivDriverB.setTag(getFileTag(imageUriDBack))
                }
                11 -> {
                    binding.ivPassenger.setImageURI(imageUriP)
                    binding.ivPassenger.setTag(getFileTag(imageUriP))
                }
                12 -> {
                    binding.ivPassengerB.setImageURI(imageUriPBack)
                    binding.ivPassengerB.setTag(getFileTag(imageUriPBack))
                }
            }
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

    //갤러리에서 사진 가져오기
    private fun loadGallery(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")

        startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
    }

}