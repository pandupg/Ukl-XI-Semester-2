package com.example.uklsemester2.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklsemester2.R
import com.example.uklsemester2.menu.minuman.entity.Minuman

class MenuMinumanEditor : AppCompatActivity() {
    private lateinit var namaMinuman: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabaseMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_minuman_editor)

        namaMinuman = findViewById(R.id.edtNamaMinuman)
        deskripsi = findViewById(R.id.edtDeskripsi)
        harga = findViewById(R.id.edtHarga)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabaseMenu.getInstance(applicationContext)

        var intent = intent.extras
        if(intent != null) {
            var id = intent.getInt("id", 0)
            var minuman = database.minumanDao().get(id)

            namaMinuman.setText(minuman.namaMinuman)
            deskripsi.setText(minuman.deskripsi)
            harga.setText(minuman.harga)
        }

        btnSave.setOnClickListener {
            if (namaMinuman.text.isNotEmpty() && deskripsi.text.isNotEmpty() && harga.text.isNotEmpty()) {
                if (intent != null){
                    database.minumanDao().update(
                        Minuman(
                        intent.getInt("id", 0),
                        namaMinuman.text.toString(),
                        deskripsi.text.toString(),
                        harga.text.toString()
                    ))
                } else {
                    database.minumanDao().insertAll(
                        Minuman(
                        null,
                            namaMinuman.text.toString(),
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