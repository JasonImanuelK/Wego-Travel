package com.example.wego_travel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.util.*

class MutableListArrayAdapter <T>(context: Context, private val list: MutableList<T>) :
    ArrayAdapter<T>(context, android.R.layout.simple_list_item_1, list), Filterable {

    private var filteredList: MutableList<T> = list.toMutableList()

    override fun getCount(): Int {
        return filteredList.size
    }

    override fun getItem(position: Int): T? {
        return filteredList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        view.findViewById<TextView>(android.R.id.text1).text = filteredList[position].toString()
        return view
    }

    override fun add(item: T?) {
        list.add(item!!)
        notifyDataSetChanged()
        filter.filter(null)
    }

    override fun remove(item: T?) {
        list.remove(item!!)
        notifyDataSetChanged()
        filter.filter(null)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val query = constraint.toString().toLowerCase(Locale.ROOT)
                val filtered = mutableListOf<T>()
                for (item in list) {
                    if (item.toString().toLowerCase(Locale.ROOT).contains(query)) {
                        filtered.add(item)
                    }
                }
                results.count = filtered.size
                results.values = filtered
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<T>
                notifyDataSetChanged()
            }
        }
    }
}