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
import com.android.r.databinding.FragmentStart2Binding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class CarSelectFragment : BaseFragment<FragmentCarSelectBinding>(R.layout.fragment_car_select) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initStartView() {
        super.initStartView()

        Glide.with(context!!)
            .load(arguments?.getString("image"))
            .into(binding.ivCarimg)
        binding.tvCarnum.text = arguments?.getString("number") ?: "number"
        binding.tvModel.text = arguments?.getString("model") ?: "model"
        binding.tvSeater.text = arguments?.getString("seater") ?: "seater"


        binding.btnBook.setOnClickListener {
            navController.navigate(R.id.action_carSelectFragment_to_start2Fragment)
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

                            binding.tvStartTime.text = "$year/$month/$dayOfMonth $hour:$min"
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

        binding.btnEndtime.setOnClickListener {
            val datepickercalendar = Calendar.getInstance()
            val year = datepickercalendar.get(Calendar.YEAR)
            val month = datepickercalendar.get(Calendar.MONTH)
            val day = datepickercalendar.get(Calendar.DAY_OF_MONTH)
            //시간
            val hour = datepickercalendar.get(Calendar.HOUR_OF_DAY)
            val min = datepickercalendar.get(Calendar.MINUTE)
            val sec = datepickercalendar.get(Calendar.SECOND)

            val dpd = DatePickerDialog(
                requireContext(),
                R.style.MySpinnerDatePickerStyle,
                { _, year, monthOfYear, dayOfMonth ->
                    val month = monthOfYear + 1
                    val calendar = Calendar.getInstance()
                    calendar.set(year, monthOfYear, dayOfMonth)

                    binding.tvEndTime.text = "$year/$month/$dayOfMonth $hour:$min:$sec"

                },
                year,
                month,
                day
            )
//           최소 날짜를 현재 시각 이후로
            dpd.datePicker.minDate = System.currentTimeMillis() - 1000;
            dpd.show()
        }
    }

}