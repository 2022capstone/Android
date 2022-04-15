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
import com.bumptech.glide.Glide
import java.net.URI

class MyCarListAdapter(carList: List<Car>, context : Context) : RecyclerView.Adapter<MyCarListAdapter.CustomViewHolder>(){

    var carList : List<Car> = carList
        set(value){
            field = value
            notifyDataSetChanged()
        }
    val context : Context = context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(
            ListCarBtnBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        Log.d("carrr", carList.toString())
        Log.d("carrr", position.toString())
        Glide.with(context)
            .load(carList.get(position).carImage)
            .into(holder.image)
        holder.model.text = carList.get(position).carModel
        holder.owner.text = carList.get(position).ownerId
        holder.date.text = carList.get(position).availableTime

        if(carList.get(position).availableStatus.equals("y")){
            holder.state.text = "대여 가능"
        }
        else{
            holder.state.text = "대여 불가능"
        }

    }

    override fun getItemCount(): Int {
        return carList.size
    }

    class CustomViewHolder(itemView: ListCarBtnBinding) : RecyclerView.ViewHolder(itemView.root) {
        val image = itemView.ivCar //차 사진
        val model = itemView.tvModel //모델
        val owner = itemView.tvOwner //차주
        val date = itemView.tvDate //날짜
        val state = itemView.btnState //대여상태
    }

}


