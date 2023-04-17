package com.example.wego_travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.HistoryHotel
import com.example.wego_travel.Models.Pesawat
import com.example.wego_travel.databinding.ItemRowPesanPesawatBinding
import java.text.SimpleDateFormat

class ListPesawatAdapter (private val listPesawat: ArrayList<Pesawat>) : RecyclerView.Adapter<ListPesawatAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowPesanPesawatBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(pesawat: Pesawat){
            with(binding){
                tvPesanPesawatName.text = pesawat.maskapai
                tvPesanPesawatDescription.setText(sdf.format(pesawat.tanggal_berangkat))
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowPesanPesawatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPesawat[position])
    }

    override fun getItemCount(): Int = listPesawat.size
}