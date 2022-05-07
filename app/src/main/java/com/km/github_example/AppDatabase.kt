package com.km.github_example

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInfoDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInfoDAO(): UserInfoDAO

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sample.db"
                ).build()
            }

            return instance!!
        }
    }
}
