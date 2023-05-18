package com.example.uklsemester2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.uklsemester2.user.UserActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataUser: Button = findViewById(R.id.btn_dataUser)
        val menu: Button = findViewById(R.id.btn_menu)
        val meja: Button = findViewById(R.id.btn_meja)

        dataUser.setOnClickListener{
            val move = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(move)
        }
//
//        menu.setOnClickListener{
//            val move = Intent(this@MainActivity, Menu::class.java)
//            startActivity(move)
//        }
//
//        meja.setOnClickListener{
//            val move = Intent(this@MainActivity, NoMeja::class.java)
//            startActivity(move)
//        }
    }
}