package com.example.wego_travel

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.Hotel
import com.example.wego_travel.databinding.ItemRowPesanHotelBinding
import java.text.SimpleDateFormat

class ListHotelAdapter (private val listHotel: ArrayList<Hotel>) : RecyclerView.Adapter<ListHotelAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowPesanHotelBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(hotel: Hotel){
            with(binding){
                tvPesanHotelName.text = hotel.nama_hotel
                tvPesanHotelDescription.text = hotel.deskripsi
            }
            itemView.setOnClickListener {
                // Get the selected data from the clicked item
                val selectedHotel = listHotel[adapterPosition]
                val moveDataHotel= Intent(itemView.context, LihatKamarHotelActivity::class.java)

                moveDataHotel.putExtra(LihatKamarHotelActivity.ID_HOTEL, selectedHotel.id_hotel.toString())
                Log.v("ID PESAWAT ADAPTER : ", selectedHotel.id_hotel.toString())
                itemView.context.startActivity(moveDataHotel)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowPesanHotelBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHotel[position])
    }

    override fun getItemCount(): Int = listHotel.size
}