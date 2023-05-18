package com.example.uklsemester2.user.dao

import androidx.room.*
import com.example.uklsemester2.user.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user WHERE uid = :uid")
    fun get(uid: Int) : User

    @Update
    fun update(user: User)
}