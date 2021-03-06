package com.android.r.ui.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.android.r.databinding.ListCarBtnBinding
import com.android.r.model.Rent
import com.bumptech.glide.Glide

class CurrentCarAdapter(rentList: List<Rent>, context : Context) : RecyclerView.Adapter<CurrentCarAdapter.CustomViewHolder>(){

    var rentList : List<Rent> = rentList
        set(value){
            field = value
            notifyDataSetChanged()
        }
    val context : Context = context

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(button: Button, position: Int)
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
            holder.owner.text = rentList.get(position).carInfo.ownerId
            holder.startdate.text = rentList.get(position).startTime
            holder.enddate.text = rentList.get(position).endTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
        }
        else if (rentList.get(position).status.equals("3")){
            holder.rentView.visibility = View.VISIBLE

            holder.state.text = "예약승인"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.owner.text = rentList.get(position).carInfo.ownerId
            holder.startdate.text = rentList.get(position).carInfo.availableStartTime
            holder.enddate.text = rentList.get(position).carInfo.availableEndTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
        }
        else if (rentList.get(position).status.equals("4")){
            holder.rentView.visibility = View.VISIBLE

            holder.state.text = "반납하기"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.owner.text = rentList.get(position).carInfo.ownerId
            holder.startdate.text = rentList.get(position).carInfo.availableStartTime
            holder.enddate.text = rentList.get(position).carInfo.availableEndTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
        }
        else if (rentList.get(position).status.equals("6")){
            holder.rentView.visibility = View.VISIBLE

            holder.state.text = "반납대기"
            Glide.with(context)
                .load(rentList.get(position).carInfo.carImage)
                .into(holder.image)
            holder.model.text = rentList.get(position).carInfo.carModel
            holder.owner.text = rentList.get(position).carInfo.ownerId
            holder.startdate.text = rentList.get(position).carInfo.availableStartTime
            holder.enddate.text = rentList.get(position).carInfo.availableEndTime
            //1 -> 예약함, 2 -> 예약 승인 받고 대여 대기중, 3 -> 사진 저장 후 대여 대기중, 4 -> 대여중, 5 -> 반납 대기중, 6 -> 사진 저장후 반납 대기중, 7 -> 반납 완료
        }
        else{
            holder.rentView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return rentList.size
    }

    class CustomViewHolder(itemView: ListCarBtnBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.ivCar //차 사진
        val model = itemView.tvModel  //모델
        val owner = itemView.tvOwner //차주
        val startdate = itemView.tvStarttime //날짜
        val enddate = itemView.tvEndtime
        val state = itemView.btnState //대여상태
        val rentView = itemView.clCarlist

        init {
            itemView.btnState.setOnClickListener {
                listener.onItemClick(state, adapterPosition)
            }
        }

    }

}


