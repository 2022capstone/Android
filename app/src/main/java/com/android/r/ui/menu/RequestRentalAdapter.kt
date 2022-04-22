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
import com.android.r.databinding.ListCarBinding
import com.android.r.databinding.ListCarBtnBinding
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            ListCarBtnBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), mListener
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (rentList.get(position).status.equals("2")){
            holder.rentView.visibility = View.VISIBLE
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
            holder.rentView.visibility = View.VISIBLE
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
            holder.rentView.visibility = View.VISIBLE
            holder.state.text = "반납승인"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.renter.text = rentList.get(position).renterId
            holder.date.text = rentList.get(position).carInfo.availableTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료

        }else{
            holder.rentView.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return rentList.size
    }

    class CustomViewHolder(itemView: ListCarBtnBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.ivCar //차 사진
        val model = itemView.tvModel  //모델
        val renter = itemView.tvOwner //빌리는 사람
        val date = itemView.tvDate //날짜
        val state = itemView.btnState //대여상태
        val rentView = itemView.clCarlist

        init {
            /*itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }*/
            itemView.btnState.setOnClickListener {
                Log.d("test","click")
                listener.onItemClick(state)
            }
        }
    }

}


