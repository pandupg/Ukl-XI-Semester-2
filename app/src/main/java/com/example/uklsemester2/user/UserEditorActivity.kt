package com.example.uklsemester2.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.uklsemester2.R
import com.example.uklsemester2.user.entity.User

class UserEditorActivity : AppCompatActivity() {
    private lateinit var namaUser: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var jobdesk: Spinner
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase
    private lateinit var jobdeskInput: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_editor)

        namaUser = findViewById(R.id.edtNamaUser)
        username = findViewById(R.id.edtUsername)
        password = findViewById(R.id.edtPassword)
        jobdesk = findViewById(R.id.edtJobdesk)
        btnSave = findViewById(R.id.btn_save)
        jobdeskInput = jobdesk.selectedItem.toString()

        database = AppDatabase.getInstance(applicationContext)

        var intent = intent.extras
        if(intent != null){
            var id = intent.getInt("id", 0)
            val user = database.userDao().get(id)

            namaUser.setText(user.namaUser)
            username.setText(user.username)
            password.setText(user.password)
        }

        btnSave.setOnClickListener {
            if (namaUser.text.isNotEmpty() && username.text.isNotEmpty() && password.text.isNotEmpty()) {
                if (intent != null){
                    database.userDao().update(User(
                        intent.getInt("id", 0),
                        namaUser.text.toString(),
                        username.text.toString(),
                        password.text.toString(),
                        jobdeskInput
                    ))
                } else {
                    database.userDao().insertAll(User(
                        null,
                        namaUser.text.toString(),
                        username.text.toString(),
                        password.text.toString(),
                        jobdeskInput
                    ))
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi semua data dengan valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}