package com.example.uklsemester2.meja

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklsemester2.meja.dao.MejaDao
import com.example.uklsemester2.meja.entity.Meja
import com.example.uklsemester2.user.AppDatabase

@Database(entities = [Meja::class], version = 2)
abstract class AppDatabaseMeja : RoomDatabase() {
    abstract fun mejaDao(): MejaDao

    companion object {
        private var instance: AppDatabaseMeja? = null

        fun getInstance(context: Context): AppDatabaseMeja {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabaseMeja::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }


            return instance!!
        }
    }
}