package com.android.r.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.r.R
import com.android.r.databinding.ListCarBinding
import com.android.r.databinding.ListCarBtnBinding
import com.android.r.model.Car
import com.android.r.ui.menu.CurrentCarAdapter
import com.android.r.ui.menu.MyCarListAdapter
import com.android.r.ui.menu.RequestRentalAdapter
import com.bumptech.glide.Glide

class CarListAdapter(carList: List<Car>, context : Context) : RecyclerView.Adapter<CarListAdapter.CustomViewHolder>(){

    var carList : List<Car> = carList
        set(value){
            field = value
            notifyDataSetChanged()
        }
    val context : Context = context

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = ListCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Glide.with(context)
            .load(carList.get(position).carImage)
            .into(holder.image)
        holder.model.text = carList.get(position).carModel
        holder.owner.text = carList.get(position).ownerId
        holder.startdate.text = carList.get(position).availableStartTime
        holder.enddate.text = carList.get(position).availableEndTime
        holder.location.text = carList.get(position).carLocation
    }

    override fun getItemCount(): Int {
        return carList.size
    }


    class CustomViewHolder(itemView: ListCarBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.ivCar //차 사진
        val model = itemView.tvModel  //모델
        val owner = itemView.tvOwner //차주
        val startdate = itemView.tvStarttime //날짜
        val enddate = itemView.tvEndtime
        val location = itemView.tvLocation //위치

        init {
            itemView.clListcar.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}


