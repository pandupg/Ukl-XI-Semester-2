package com.example.uklsemester2.menu

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.uklsemester2.MainActivity
import com.example.uklsemester2.R
import com.example.uklsemester2.menu.makanan.adapter.MakananAdapter
import com.example.uklsemester2.menu.makanan.entity.Makanan
import com.example.uklsemester2.menu.minuman.adapter.MinumanAdapter
import com.example.uklsemester2.menu.minuman.entity.Minuman
import com.example.uklsemester2.user.AppDatabase
import com.example.uklsemester2.user.UserEditorActivity
import com.example.uklsemester2.user.adapter.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuActivity : AppCompatActivity() {
    private lateinit var recyclerViewMakanan: RecyclerView
    private lateinit var recyclerViewMinuman: RecyclerView
    private lateinit var btnAddMakanan: Button
    private lateinit var btnAddMinuman: Button
    private var listMakanan = mutableListOf<Makanan>()
    private var listMinuman = mutableListOf<Minuman>()
    private lateinit var adapterMakanan: MakananAdapter
    private lateinit var adapterMinuman: MinumanAdapter
    private lateinit var database: AppDatabaseMenu
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        recyclerViewMakanan = findViewById(R.id.recycler_view_makanan)
        recyclerViewMinuman = findViewById(R.id.recycler_view_minuman)
        btnAddMakanan = findViewById(R.id.btnAddMakanan)
        btnAddMinuman = findViewById(R.id.btnAddMinuman)

        database = AppDatabaseMenu.getInstance(applicationContext)

        adapterMakanan = MakananAdapter(listMakanan)
        adapterMakanan.setDialog(object : MakananAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MenuActivity)
                dialog.setTitle(listMakanan[position].namaMakanan)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if(which == 0) {
                        val intent = Intent(this@MenuActivity, MenuMakananEditor::class.java)
                        intent.putExtra("id", listMakanan[position].uid)
                        startActivity(intent)
                    } else if(which == 1) {
                        database.makananDao().delete(listMakanan[position])
                        getDataMakanan()
                    } else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        adapterMinuman = MinumanAdapter(listMinuman)
        adapterMinuman.setDialog(object : MinumanAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MenuActivity)
                dialog.setTitle(listMinuman[position].namaMinuman)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if(which == 0) {
                        val intent = Intent(this@MenuActivity, MenuMinumanEditor::class.java)
                        intent.putExtra("id", listMinuman[position].uid)
                        startActivity(intent)
                    } else if(which == 1) {
                        database.minumanDao().delete(listMinuman[position])
                        getDataMinuman()
                    } else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }
        })

        recyclerViewMakanan.adapter = adapterMakanan
        recyclerViewMakanan.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerViewMakanan.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        recyclerViewMinuman.adapter = adapterMinuman
        recyclerViewMinuman.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerViewMinuman.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        btnAddMakanan.setOnClickListener{
            startActivity(Intent(this, MenuMakananEditor::class.java))
        }

        btnAddMinuman.setOnClickListener{
            startActivity(Intent(this, MenuMinumanEditor::class.java))
        }

        btnBack = findViewById(R.id.backFromMenu)
        btnBack. setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getDataMakanan()
        getDataMinuman()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getDataMakanan() {
        listMakanan.clear()
        listMakanan.addAll(database.makananDao().getAll())
        adapterMakanan.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getDataMinuman() {
        listMinuman.clear()
        listMinuman.addAll(database.minumanDao().getAll())
        adapterMinuman.notifyDataSetChanged()
    }
}