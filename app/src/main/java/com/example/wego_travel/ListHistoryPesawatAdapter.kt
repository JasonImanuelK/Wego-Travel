package com.example.wego_travel

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.HistoryPesawat
import com.example.wego_travel.databinding.ItemRowHistoryPesawatBinding
import java.text.SimpleDateFormat

class ListHistoryPesawatAdapter (private val listHistoryPesawat: ArrayList<HistoryPesawat>) : RecyclerView.Adapter<ListHistoryPesawatAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowHistoryPesawatBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(historyPesawat: HistoryPesawat){
            with(binding){
                tvHistoryPesawatName.text = historyPesawat.maskapai
                tvStatusPemesanan.text = historyPesawat.status_pemesanan
                tvHistoryPesawatDescription.setText(sdf.format(historyPesawat.tanggal_pemesanan))
            }
            itemView.setOnClickListener {
                // Get the selected data from the clicked item
                val selectedHistoryPesawat = listHistoryPesawat[adapterPosition]
                val moveDataHistoryPesawat = Intent(itemView.context, TampilanHistoryPesawatActivity::class.java)

                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.ID_TIKET_PESAWAT, selectedHistoryPesawat.id_tiket_pesawat.toString())
                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.MASKAPAI, selectedHistoryPesawat.maskapai)
                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.TANGAL_PEMESANAN, selectedHistoryPesawat.tanggal_pemesanan.toString())
                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.STATUS_PEMESANAN, selectedHistoryPesawat.status_pemesanan)
                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.ID_VOUCHER, selectedHistoryPesawat.id_voucher.toString())
                itemView.context.startActivity(moveDataHistoryPesawat)
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

    override fun getItemCount(): Int{
        return listHistoryPesawat.size
    }
}