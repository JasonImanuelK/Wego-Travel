package com.example.wego_travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.HistoryHotel
import com.example.wego_travel.databinding.ItemRowHistoryHotelBinding
import java.text.SimpleDateFormat

class ListHistoryHotelAdapter (private val listHistoryHotel: ArrayList<HistoryHotel>) : RecyclerView.Adapter<ListHistoryHotelAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowHistoryHotelBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(historyHotel: HistoryHotel){
            with(binding){
                tvHistoryHotelName.text = historyHotel.nama_hotel
                tvStatusPemesanan.text = historyHotel.status_pemesanan
                tvHistoryHotelDescription.setText(sdf.format(historyHotel.tanggal_pemesanan))
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