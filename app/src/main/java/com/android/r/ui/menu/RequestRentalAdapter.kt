package com.android.r.ui.menu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.r.R
import com.android.r.model.Car
import com.android.r.model.Rent
import com.android.r.ui.CarList
import com.bumptech.glide.Glide

class RequestRentalAdapter(rentList: List<Rent>, context : Context) : RecyclerView.Adapter<RequestRentalAdapter.CustomViewHolder>(){

    var rentList : List<Rent> = rentList
        set(value){
            field = value
            notifyDataSetChanged()
        }
    val context : Context = context

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Button)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_car_btn, parent, false)
        return CustomViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (rentList.get(position).status.equals("2")){
            holder.state.text = "예약대기"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.renter.text = rentList.get(position).renterId
            holder.date.text = rentList.get(position).carInfo.availableTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료

        }
        else if (rentList.get(position).status.equals("3")){
            holder.state.text = "예약승인"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.renter.text = rentList.get(position).renterId
            holder.date.text = rentList.get(position).carInfo.availableTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료

        }
        else if (rentList.get(position).status.equals("6")){
            holder.state.text = "반납대기"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.renter.text = rentList.get(position).renterId
            holder.date.text = rentList.get(position).carInfo.availableTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료

        }

    }

    override fun getItemCount(): Int {
        return rentList.size
    }

    class CustomViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_car) //차 사진
        val model = itemView.findViewById<TextView>(R.id.tv_model)  //모델
        val renter = itemView.findViewById<TextView>(R.id.tv_owner) //빌리는 사람
        val date = itemView.findViewById<TextView>(R.id.tv_date) //날짜
        val state = itemView.findViewById<Button>(R.id.btn_state) //대여상태

        init {
            /*itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }*/
            itemView.findViewById<Button>(R.id.btn_state).setOnClickListener {
                Log.d("test","click")
                listener.onItemClick(state)
            }
        }
    }

}


