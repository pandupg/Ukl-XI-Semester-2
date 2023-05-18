package com.example.uklsemester2.menu.minuman.dao

import androidx.room.*
import com.example.uklsemester2.menu.minuman.entity.Minuman
import com.example.uklsemester2.user.entity.User

@Dao
interface MinumanDao {
    @Query("SELECT * FROM minuman")
    fun getAll(): List<Minuman>

    @Insert
    fun insertAll(vararg minuman: Minuman)

    @Delete
    fun delete(minuman: Minuman)

    @Query("SELECT * FROM minuman WHERE uid = :uid")
    fun get(uid: Int) : Minuman

    @Update
    fun update(minuman: Minuman)
}