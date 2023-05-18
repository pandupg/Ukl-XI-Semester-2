package com.example.uklsemester2.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklsemester2.R
import com.example.uklsemester2.menu.makanan.entity.Makanan

class MenuMakananEditor : AppCompatActivity() {
    private lateinit var namaMakanan: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_makanan_editor)

        namaMakanan = findViewById(R.id.edtNamaMakanan)
        deskripsi = findViewById(R.id.edtDeskripsi)
        harga = findViewById(R.id.edtHarga)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if(intent != null) {
            var id = intent.getInt("id", 0)
            var makanan = database.makananDao().get(id)

            namaMakanan.setText(makanan.namaMakanan)
            deskripsi.setText(makanan.deskripsi)
            harga.setText(makanan.harga)
        }

        btnSave.setOnClickListener{
            if(namaMakanan.text.isNotEmpty() && deskripsi.text.isNotEmpty() && harga.text.isNotEmpty()) {
                if(intent != null) {
                    database.makananDao().update(Makanan(
                        intent.getInt("id", 0),
                        namaMakanan.text.toString(),
                        deskripsi.text.toString(),
                        harga.text.toString()
                    ))
                } else {
                    database.makananDao().insertAll(Makanan(
                        null,
                        namaMakanan.text.toString(),
                        deskripsi.text.toString(),
                        harga.text.toString()
                    ))
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data dengan valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}