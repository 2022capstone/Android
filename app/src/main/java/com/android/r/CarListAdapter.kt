package com.android.r

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarListAdapter(val carList: ArrayList<CarList>) : RecyclerView.Adapter<CarListAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarListAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_car, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarListAdapter.CustomViewHolder, position: Int) {
        holder.image.setImageResource(carList.get(position).image)
        holder.model.text = carList.get(position).model
        holder.owner.text = carList.get(position).owner
        holder.date.text = carList.get(position).date
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.iv_car)//사진
        val model = itemView.findViewById<TextView>(R.id.tv_model)
        val owner = itemView.findViewById<TextView>(R.id.tv_owner)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

}