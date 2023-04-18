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
//            itemView.setOnClickListener {
//                // Get the selected data from the clicked item
//                val selectedHistoryPesawat = listHistoryPesawat[adapterPosition]
//                val moveDataHistoryPesawat = Intent(itemView.context, TampilanHistoryPesawatActivity::class.java)
//
//                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.ID_TIKET_PESAWAT, selectedHistoryPesawat.id_tiket_pesawat.toString())
//                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.MASKAPAI, selectedHistoryPesawat.maskapai)
//                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.TANGAL_PEMESANAN, selectedHistoryPesawat.tanggal_pemesanan.toString())
//                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.STATUS_PEMESANAN, selectedHistoryPesawat.status_pemesanan)
//                moveDataHistoryPesawat.putExtra(TampilanHistoryPesawatActivity.ID_VOUCHER, selectedHistoryPesawat.id_voucher.toString())
//                itemView.context.startActivity(moveDataHistoryPesawat)
//            }
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