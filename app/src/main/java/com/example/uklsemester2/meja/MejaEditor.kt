package com.example.uklsemester2.meja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uklsemester2.R
import com.example.uklsemester2.meja.entity.Meja

class MejaEditor : AppCompatActivity() {
    private lateinit var noMeja: EditText
    private lateinit var btnSave: Button
    private lateinit var databaseMeja: AppDatabaseMeja

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meja_editor)

        noMeja = findViewById(R.id.edtNoMeja)

        databaseMeja = AppDatabaseMeja.getInstance(applicationContext)

        var intent = intent.extras
        if(intent != null) {
            var id = intent.getInt("id", 0)
            val meja = databaseMeja.mejaDao().get(id)

            noMeja.setText(meja.noMeja)
        }

        btnSave = findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            if(noMeja.text.isNotEmpty()){
                if (intent != null){
                    databaseMeja.mejaDao().update(Meja(
                        intent.getInt("id", 0),
                        noMeja.text.toString()
                    ))
                } else {
                    databaseMeja.mejaDao().insertAll(Meja(
                        null,
                        noMeja.text.toString()
                    ))
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data dengan valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}