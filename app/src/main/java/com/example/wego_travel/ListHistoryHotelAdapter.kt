package com.example.wego_travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.HistoryHotel
import com.example.wego_travel.databinding.ItemRowHistoryHotelBinding

class ListHistoryHotelAdapter (private val listHistoryHotel: ArrayList<HistoryHotel>) : RecyclerView.Adapter<ListHistoryHotelAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowHistoryHotelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyHotel: HistoryHotel){
            with(binding){
                tvHistoryHotelName.text = historyHotel.id_tiket_hotel.toString()
                tvHistoryHotelDescription.text = historyHotel.tanggal_pemesanan.toString()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowHistoryHotelBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHistoryHotel[position])
    }

    override fun getItemCount(): Int = listHistoryHotel.size
}