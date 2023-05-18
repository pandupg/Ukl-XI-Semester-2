package com.example.uklsemester2.menu.makanan.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Makanan(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "namaMakanan") var namaMakanan: String?,
    @ColumnInfo(name = "deskripsi") var deskripsi: String?,
    @ColumnInfo(name = "harga") var harga: String?
)
