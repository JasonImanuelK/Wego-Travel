package com.example.wego_travel

import android.content.Intent
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

            itemView.setOnClickListener {
                // Get the selected data from the clicked item
                val selectedHistoryHotel = listHistoryHotel[adapterPosition]
                val moveDataHistoryHotel= Intent(itemView.context, TampilanHistoryHotelActivity::class.java)

                moveDataHistoryHotel.putExtra(TampilanHistoryHotelActivity.ID_TIKET_HOTEL, selectedHistoryHotel.id_tiket_hotel.toString())
                moveDataHistoryHotel.putExtra(TampilanHistoryHotelActivity.NAMA_HOTEL, selectedHistoryHotel.nama_hotel)
                moveDataHistoryHotel.putExtra(TampilanHistoryHotelActivity.TANGAL_PEMESANAN, selectedHistoryHotel.tanggal_pemesanan.toString())
                moveDataHistoryHotel.putExtra(TampilanHistoryHotelActivity.STATUS_PEMESANAN, selectedHistoryHotel.status_pemesanan)
                moveDataHistoryHotel.putExtra(TampilanHistoryHotelActivity.ID_VOUCHER, selectedHistoryHotel.id_voucher.toString())
                itemView.context.startActivity(moveDataHistoryHotel)
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