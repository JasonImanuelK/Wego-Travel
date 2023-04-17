package com.example.wego_travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.HistoryPesawat
import com.example.wego_travel.databinding.ItemRowHistoryPesawatBinding
import java.text.SimpleDateFormat

class ListHistoryPesawatAdapter (private val listHistoryPesawat: ArrayList<HistoryPesawat>) : RecyclerView.Adapter<ListHistoryPesawatAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowHistoryPesawatBinding) : RecyclerView.ViewHolder(binding.root) {
//        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(historyPesawat: HistoryPesawat){
            with(binding){
                tvHistoryPesawatName.text = historyPesawat.maskapai
                tvStatusPemesanan.text = historyPesawat.status_pemesanan
//                tvHistoryPesawatDescription.setText(sdf.format(historyPesawat.tanggal_pemesanan))
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowHistoryPesawatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHistoryPesawat[position])
    }

    override fun getItemCount(): Int = listHistoryPesawat.size
}