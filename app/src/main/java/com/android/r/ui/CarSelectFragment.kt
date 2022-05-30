package com.android.r.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface.BUTTON_POSITIVE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.r.R
import com.android.r.base.BaseFragment
import com.android.r.databinding.FragmentCarSelectBinding
import com.android.r.model.Customer
import com.android.r.model.Rent
import com.android.r.model.RentInfo
import com.android.r.viewmodel.RentViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.LocalDateTime
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class CarSelectFragment : BaseFragment<FragmentCarSelectBinding>(R.layout.fragment_car_select) {
    private val rentViewModel : RentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {

        Glide.with(context!!)
            .load(arguments?.getString("image"))
            .into(binding.ivCarimg)
        binding.tvCarnum.text = arguments?.getString("number") ?: "number"
        binding.tvModel.text = arguments?.getString("model") ?: "model"
        binding.tvSeater.text = arguments?.getString("seater") ?: "seater"


        binding.btnBook.setOnClickListener {


            if(binding.tvStartTime.text.equals("2022/00/00 00:00") && binding.tvEndTime.text.equals("2022/00/00 00:00")){
                Snackbar.make(this.view!!, "날짜를 선택해주세요.", 1000)
            }else{
                rentViewModel.insertRentInfo(
                    RentInfo(0,
                        "nyh710",
                        binding.tvCarnum.text.toString(),
                        LocalDateTime.parse(binding.tvStartTime.text.toString(), org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                        LocalDateTime.parse(binding.tvEndTime.text.toString(), org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")),
                        "1",
                        "0.0".toFloat(),
                        ""
                    )
                )
                navController.navigate(R.id.action_carSelectFragment_to_start2Fragment)

            }

        }

        //calendar
        binding.btnStarttime.setOnClickListener {
            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                requireContext(),
                R.style.MySpinnerDatePickerStyle,
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1
                    val calendar = Calendar.getInstance()
                    calendar.set(year, monthOfYear, dayOfMonth)

                    val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, min ->
                            datepickercalendar.set(Calendar.HOUR_OF_DAY, hour)
                            datepickercalendar.set(Calendar.MINUTE, min)
                        var fomattedHour : String = hour.toString()
                        var fomattedMonth : String = month.toString()
                        var fomattedMinute : String = min.toString()
                        var fomattedDay : String = day.toString()

                        if (hour < 10){
                            fomattedHour = "0" + hour.toString()
                        }
                        if (month < 10){
                            fomattedMonth = "0" + month.toString()
                        }
                        if (min < 10){
                            fomattedMinute = "0" + min.toString()
                        }
                        if (dayOfMonth < 10){
                            fomattedDay = "0" + dayOfMonth.toString()
                        }
                            binding.tvStartTime.text = "$year/$fomattedMonth/$fomattedDay $fomattedHour:$fomattedMinute"
                        }

                    TimePickerDialog(context, R.style.MySpinnerDatePickerStyle, timeSetListener, datepickercalendar.get(Calendar.HOUR_OF_DAY), datepickercalendar.get(Calendar.MINUTE), true).show()

                },
                year,
                month,
                day
            )
            //최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        binding.btnEndtime.setOnClickListener {
            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                requireContext(),
                R.style.MySpinnerDatePickerStyle,
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1
                    val calendar = Calendar.getInstance()
                    calendar.set(year, monthOfYear, dayOfMonth)

                    val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, min ->
                        datepickercalendar.set(Calendar.HOUR_OF_DAY, hour)
                        datepickercalendar.set(Calendar.MINUTE, min)

                        var fomattedHour : String = hour.toString()
                        var fomattedMonth : String = month.toString()
                        var fomattedMinute : String = min.toString()
                        var fomattedDay : String = day.toString()

                        if (hour < 10){
                            fomattedHour = "0" + hour.toString()
                        }
                        if (month < 10){
                            fomattedMonth = "0" + month.toString()
                        }
                        if (min < 10){
                            fomattedMinute = "0" + min.toString()
                        }
                        if (dayOfMonth < 10){
                            fomattedDay = "0" + dayOfMonth.toString()
                        }
                        binding.tvEndTime.text = "$year/$fomattedMonth/$fomattedDay $fomattedHour:$fomattedMinute"
                    }

                    TimePickerDialog(context, R.style.MySpinnerDatePickerStyle, timeSetListener, datepickercalendar.get(Calendar.HOUR_OF_DAY), datepickercalendar.get(Calendar.MINUTE), true).show()

                },
                year,
                month,
                day
            )
//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }

        super.initStartView()

    }

}