package com.example.wego_travel

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.HistoryPesawat
import com.example.wego_travel.Models.KursiPesawat
import com.example.wego_travel.databinding.ItemRowKursiPesawatBinding
import java.text.SimpleDateFormat

class ListLihatKursiAdapter (private val listKursiPesawat: ArrayList<KursiPesawat>) : RecyclerView.Adapter<ListLihatKursiAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowKursiPesawatBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(kursiPesawat: KursiPesawat){
            with(binding){
                tvNomorKursi.text = kursiPesawat.nomor_kursi
                tvTipeKursi.text = kursiPesawat.tipe_kursi
                tvStatusKursi.text = kursiPesawat.status_kursi
                tvHargaKursi.text = kursiPesawat.harga_kursi.toString()
            }
            itemView.setOnClickListener {
                // Get the selected data from the clicked item
                val selectedKursiPesawat = listKursiPesawat[adapterPosition]
                val moveKursiPesawat= Intent(itemView.context, IsiDataPesanPesawatActivity::class.java)

                moveKursiPesawat.putExtra(IsiDataPesanPesawatActivity.KURSI_PESAWAT, selectedKursiPesawat.nomor_kursi.toString())
                itemView.context.startActivity(moveKursiPesawat)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowKursiPesawatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listKursiPesawat[position])
    }

    override fun getItemCount(): Int{
        return listKursiPesawat.size
    }
}