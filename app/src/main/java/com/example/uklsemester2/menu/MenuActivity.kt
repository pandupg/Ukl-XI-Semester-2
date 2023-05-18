package com.example.uklsemester2.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.uklsemester2.R
import com.example.uklsemester2.menu.makanan.adapter.MakananAdapter
import com.example.uklsemester2.menu.makanan.entity.Makanan
import com.example.uklsemester2.menu.minuman.adapter.MinumanAdapter
import com.example.uklsemester2.menu.minuman.entity.Minuman
import com.example.uklsemester2.user.AppDatabase
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
    private lateinit var database: AppDatabase
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        recyclerViewMakanan = findViewById(R.id.recycler_view_makanan)
        recyclerViewMinuman = findViewById(R.id.recycler_view_minuman)
        btnAddMakanan = findViewById(R.id.btnAddMakanan)
        btnAddMinuman = findViewById(R.id.btnAddMinuman)

        database = AppDatabase.getInstance(applicationContext)
        adapterMakanan = MakananAdapter(listMakanan)
        adapterMakanan.setDialog(object : MakananAdapter.Dialog{
            val dialog = AlertDialog.Builder(this@MenuActivity)

            override fun onClick(position: Int) {
                TODO("Not yet implemented")
            }

        })

    }
}