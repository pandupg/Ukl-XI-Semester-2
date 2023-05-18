package com.example.uklsemester2.user.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.findColumnIndexBySuffix
import com.example.uklsemester2.R
import com.example.uklsemester2.user.entity.User

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namaUser: TextView
        var username: TextView
        var password: TextView
        var jobdesk : TextView

        init {
            namaUser = view.findViewById(R.id.namaUser)
            username = view.findViewById(R.id.username)
            password = view.findViewById(R.id.password)
            jobdesk = view.findViewById(R.id.jobdesk)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.namaUser.text = list[position].namaUser
        holder.username.text = list[position].username
        holder.password.text = list[position].password
        holder.jobdesk.text = list[position].jobdesk
    }
}