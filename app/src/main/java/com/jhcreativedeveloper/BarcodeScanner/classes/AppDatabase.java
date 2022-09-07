package com.jhcreativedeveloper.BarcodeScanner.classes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jhcreativedeveloper.BarcodeScanner.UserDao;


    @Database(entities = {MyDataBase.class},version = 1)
    public abstract class AppDatabase extends RoomDatabase{
        public abstract UserDao userDao();

}
