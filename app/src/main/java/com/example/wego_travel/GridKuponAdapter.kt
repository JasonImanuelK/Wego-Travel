package com.example.wego_travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.Kupon
import com.example.wego_travel.databinding.ItemGridKuponBinding

class GridKuponAdapter (private val listKupon: ArrayList<Kupon>) : RecyclerView.Adapter<GridKuponAdapter.GridViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val binding = ItemGridKuponBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(binding)
    }

    override fun getItemCount(): Int = listKupon.size

    inner class GridViewHolder ( private val binding : ItemGridKuponBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kupon: Kupon) {
            with(binding) {
                tvNamaKupon.text = kupon.nama_voucher
                tvStatusKupon.text = kupon.status_penggunaan
            }
        }
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listKupon[position])
    }
}