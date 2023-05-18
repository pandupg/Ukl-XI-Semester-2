package com.example.uklsemester2.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklsemester2.menu.makanan.dao.MakananDao
import com.example.uklsemester2.menu.makanan.entity.Makanan
import com.example.uklsemester2.menu.minuman.dao.MinumanDao
import com.example.uklsemester2.menu.minuman.entity.Minuman

@Database(entities = [Makanan::class, Minuman::class], version = 1)
abstract class AppDatabaseMenu: RoomDatabase() {
    abstract fun makananDao(): MakananDao
    abstract fun minumanDao(): MinumanDao

    companion object {
        private var instance: AppDatabaseMenu? = null

        fun getInstance(context: Context): AppDatabaseMenu {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabaseMenu::class.java, "app-database-menu")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }


            return instance!!
        }
    }
}