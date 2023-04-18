package com.example.wego_travel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wego_travel.Models.KamarHotel
import com.example.wego_travel.Models.KursiPesawat
import com.example.wego_travel.databinding.ItemRowKamarHotelBinding
import java.text.SimpleDateFormat

class ListLihatKamarHotelAdapter (private val listKamarHotel: ArrayList<KamarHotel>) : RecyclerView.Adapter<ListLihatKamarHotelAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowKamarHotelBinding) : RecyclerView.ViewHolder(binding.root) {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        fun bind(kamarHotel: KamarHotel){
            with(binding){
                tvNomorKamar.text = kamarHotel.nomor_kamar
                tvTipeKamar.text = kamarHotel.tipe_kamar
                tvStatusKamar.text = kamarHotel.status_kamar
                tvHargaKamar.text = kamarHotel.harga_kamar.toString()
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
        val binding = ItemRowKamarHotelBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listKamarHotel[position])
    }

    override fun getItemCount(): Int{
        return listKamarHotel.size
    }
}