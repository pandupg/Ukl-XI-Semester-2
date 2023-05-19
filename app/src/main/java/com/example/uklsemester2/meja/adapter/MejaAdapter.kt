package com.example.uklsemester2.meja.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklsemester2.R
import com.example.uklsemester2.meja.entity.Meja
import com.example.uklsemester2.user.adapter.UserAdapter

class MejaAdapter(var listMeja: List<Meja>) : RecyclerView.Adapter<MejaAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var noMeja: TextView

        init {
            noMeja = view.findViewById(R.id.noMeja)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_meja, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noMeja.text = listMeja[position].noMeja
    }

    override fun getItemCount(): Int {
        return listMeja.size
    }
}