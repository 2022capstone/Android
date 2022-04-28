package com.android.r.ui.menu

import android.content.Context
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
import com.android.r.model.Rent
import com.android.r.ui.CarList
import com.bumptech.glide.Glide

class ReservationAdapter(rentList: List<Rent>, context : Context) : RecyclerView.Adapter<ReservationAdapter.CustomViewHolder>(){

    var rentList : List<Rent> = rentList
        set(value){
            field = value
            notifyDataSetChanged()
        }
    val context : Context = context

    private lateinit var mListener: onItemClickListener

    interface  onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            ListCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), mListener
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
        if (rentList.get(position).status.equals("1")){
            holder.rentView.visibility = View.VISIBLE

            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.owner.text = rentList.get(position).carInfo.ownerId
            holder.location.text = rentList.get(position).carInfo.carLocation
            holder.startdate.text = rentList.get(position).carInfo.availableStartTime
            holder.enddate.text = rentList.get(position).carInfo.availableEndTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
        }else{
            holder.rentView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return rentList.size
    }

    class CustomViewHolder(itemView: ListCarBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.ivCar //차 사진
        val model = itemView.tvModel  //모델
        val owner = itemView.tvOwner //차주
        val startdate = itemView.tvStarttime //날짜
        val enddate = itemView.tvEndtime
        val location = itemView.tvLocation
        val rentView = itemView.clListcar

        init {
            itemView.clListcar.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}


