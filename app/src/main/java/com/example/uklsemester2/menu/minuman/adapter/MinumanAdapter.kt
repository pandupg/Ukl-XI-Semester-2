package com.example.uklsemester2.menu.minuman.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklsemester2.R
import com.example.uklsemester2.menu.makanan.adapter.MakananAdapter
import com.example.uklsemester2.menu.minuman.entity.Minuman

class MinumanAdapter(var list: List<Minuman>) : RecyclerView.Adapter<MinumanAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namaMinuman: TextView
        var deskripsi: TextView
        var harga: TextView

        init {
            namaMinuman = view.findViewById(R.id.namaMinuman)
            deskripsi = view.findViewById(R.id.deskripsi)
            harga = view.findViewById(R.id.harga)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu_minuman, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaMinuman.text = list[position].namaMinuman
        holder.deskripsi.text = list[position].deskripsi
        holder.harga.text = list[position].harga
    }

    override fun getItemCount(): Int {
        return list.size
    }
}