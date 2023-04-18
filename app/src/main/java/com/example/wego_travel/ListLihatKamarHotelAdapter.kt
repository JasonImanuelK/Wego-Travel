package com.example.wego_travel

import android.content.Intent
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
            itemView.setOnClickListener {
                // Get the selected data from the clicked item
                val selectedNomorKamar = listKamarHotel[adapterPosition]
                val moveNomorKamar = Intent(itemView.context, IsiDataPesanHotelActivity::class.java)

                moveNomorKamar.putExtra(IsiDataPesanHotelActivity.NOMOR_KAMAR, selectedNomorKamar.nomor_kamar.toString())
                itemView.context.startActivity(moveNomorKamar)
            }
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