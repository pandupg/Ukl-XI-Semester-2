package com.example.uklsemester2.user.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "namaUser") var namaUser: String?,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "jobdesk") var jobdesk: String?
)