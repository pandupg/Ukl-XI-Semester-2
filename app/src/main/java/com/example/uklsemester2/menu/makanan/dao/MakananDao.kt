package com.example.uklsemester2.menu.makanan.dao

import androidx.room.*
import com.example.uklsemester2.menu.makanan.entity.Makanan
import com.example.uklsemester2.user.entity.User

@Dao
interface MakananDao {
    @Query("SELECT * FROM makanan")
    fun getAll(): List<Makanan>

    @Insert
    fun insertAll(vararg makanan: Makanan)

    @Delete
    fun delete(makanan: Makanan)

    @Query("SELECT * FROM makanan WHERE uid = :uid")
    fun get(uid: Int) : Makanan

    @Update
    fun update(makanan: Makanan)
}