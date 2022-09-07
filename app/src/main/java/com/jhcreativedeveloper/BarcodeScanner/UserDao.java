package com.jhcreativedeveloper.BarcodeScanner;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jhcreativedeveloper.BarcodeScanner.classes.MyDataBase;

import java.util.List;


@Dao
public interface UserDao {

    @Insert
    void insertRecord(MyDataBase database);

    @Query("SELECT * FROM MyDataBase")
    List<MyDataBase> getAllData();
}
