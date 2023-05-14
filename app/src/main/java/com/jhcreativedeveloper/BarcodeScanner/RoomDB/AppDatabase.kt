package com.jhcreativedeveloper.BarcodeScanner.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyDataBase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?
}