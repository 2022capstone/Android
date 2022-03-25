package com.android.r.ui.menu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.r.R
import com.android.r.ui.CarList

class CurrentCarAdapter(val carList: ArrayList<CarList>, usageDetailFragment: UsageDetailFragment) : RecyclerView.Adapter<CurrentCarAdapter.CustomViewHolder>(){

    private lateinit var mListener : onItemClickListener

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
        holder.image.setImageResource(carList.get(position).image)
        holder.model.text = carList.get(position).model
        holder.owner.text = carList.get(position).owner
        holder.date.text = carList.get(position).date
        holder.state.text = carList.get(position).state
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    class CustomViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_car) //차 사진
        val model = itemView.findViewById<TextView>(R.id.tv_model)  //모델
        val owner = itemView.findViewById<TextView>(R.id.tv_owner) //차주
        val date = itemView.findViewById<TextView>(R.id.tv_date) //날짜
        val state = itemView.findViewById<Button>(R.id.btn_state) //대여상태

        init {
            itemView.findViewById<Button>(R.id.btn_state).setOnClickListener {
                //Log.d("test","click")
                listener.onItemClick(state)
            }
        }

    }

}


