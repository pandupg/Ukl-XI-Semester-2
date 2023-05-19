package com.example.uklsemester2.meja

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.uklsemester2.MainActivity
import com.example.uklsemester2.R
import com.example.uklsemester2.meja.adapter.MejaAdapter
import com.example.uklsemester2.meja.entity.Meja
import com.example.uklsemester2.user.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MejaActivity : AppCompatActivity() {
    private lateinit var recyclerViewMeja: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Meja>()
    private lateinit var adapter: MejaAdapter
    private lateinit var database: AppDatabaseMeja
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja)
        recyclerViewMeja = findViewById(R.id.recycler_view_meja)
        fab = findViewById(R.id.fab_meja)

        database = AppDatabaseMeja.getInstance(applicationContext)
        adapter = MejaAdapter(list)
        adapter.setDialog(object  : MejaAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MejaActivity)
                dialog.setTitle(list[position].noMeja)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if(which == 0) {
                        val intent = Intent(this@MejaActivity, MejaEditor::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if(which == 1) {
                        database.mejaDao().delete(list[position])
                        getData()
                    } else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerViewMeja.adapter = adapter
        recyclerViewMeja.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerViewMeja.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, MejaEditor::class.java))
        }

        btnBack = findViewById(R.id.backFromMeja)
        btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.clear()
        list.addAll(database.mejaDao().getAll())
        adapter.notifyDataSetChanged()
    }
}