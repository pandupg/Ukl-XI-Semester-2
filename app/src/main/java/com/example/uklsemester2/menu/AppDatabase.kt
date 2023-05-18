package com.example.uklsemester2.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklsemester2.menu.makanan.dao.MakananDao
import com.example.uklsemester2.menu.makanan.entity.Makanan
import com.example.uklsemester2.menu.minuman.dao.MinumanDao
import com.example.uklsemester2.menu.minuman.entity.Minuman
import com.example.uklsemester2.user.dao.UserDao
import com.example.uklsemester2.user.entity.User

@Database(entities = [Makanan::class], [Minuman::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun makananDao(): MakananDao
    abstract fun minumanDao(): MinumanDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }


            return instance!!
        }
    }
}