package com.example.uklsemester2.meja.dao

import androidx.room.*
import com.example.uklsemester2.meja.entity.Meja

@Dao
interface MejaDao {
    @Query("SELECT * FROM meja")
    fun getAll(): List<Meja>

    @Insert
    fun insertAll(vararg meja: Meja)

    @Delete
    fun delete(meja: Meja)

    @Query("SELECT * FROM meja WHERE uid = :uid")
    fun get(uid: Int) : Meja

    @Update
    fun update(meja: Meja)
}