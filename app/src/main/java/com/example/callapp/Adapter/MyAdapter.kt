package com.example.callapp.Adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.callapp.Modul.User
import com.example.callapp.R
import com.example.callapp.databinding.ActivityAddBinding

class MyAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    // View Holder Class

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val userName: TextView = itemView.findViewById(R.id.tvName)
        val mobileNumber: TextView = itemView.findViewById(R.id.tvMobileNumber)
        val address: TextView = itemView.findViewById(R.id.tvaddress)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.userName.text = userList[position].name
        holder.mobileNumber.text = userList[position].number
        holder.address.text = userList[position].adress


    }

    override fun getItemCount(): Int {
        return userList.size
    }
}