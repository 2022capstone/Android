package com.android.r.ui.menu

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.r.R
import com.android.r.databinding.ListCarBtnBinding
import com.android.r.model.Car
import com.android.r.model.CarInfoResponse
import com.android.r.ui.CarList
import com.android.r.ui.CarListAdapter
import com.bumptech.glide.Glide
import java.net.URI

class MyCarListAdapter(carList: List<Car>, context : Context) : RecyclerView.Adapter<MyCarListAdapter.CustomViewHolder>(){

    var carList : List<Car> = carList
        set(value){
            field = value
            notifyDataSetChanged()
        }
    val context : Context = context

    private lateinit var mListener: onItemClickListener

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
        Glide.with(context)
            .load(carList.get(position).carImage)
            .into(holder.image)
        holder.model.text = carList.get(position).carModel
        holder.owner.text = carList.get(position).ownerId
        holder.startdate.text = carList.get(position).availableStartTime
        holder.enddate.text = carList.get(position).availableEndTime

        if(carList.get(position).availableStatus.equals("y")){
            holder.state.text = "대여가능"
        }
        else if(carList.get(position).availableStatus.equals("n")){
            holder.state.text = "대여불가능"
        }

    }

    override fun getItemCount(): Int {
        return carList.size
    }

    class CustomViewHolder(itemView: ListCarBtnBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.ivCar //차 사진
        val model = itemView.tvModel //모델
        val owner = itemView.tvOwner //차주
        val startdate = itemView.tvStarttime //날짜
        val enddate = itemView.tvEndtime
        val state = itemView.btnState //대여상태

        init {
            itemView.btnState.setOnClickListener {
                listener.onItemClick(state, adapterPosition)
            }
        }
    }
}


