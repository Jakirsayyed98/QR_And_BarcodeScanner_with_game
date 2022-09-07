package com.jhcreativedeveloper.BarcodeScanner.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class MyDataBase {

    @PrimaryKey(autoGenerate = true)
    public int ID;

    @ColumnInfo(name="data")
    public String data;

    public MyDataBase(int ID, String data) {
        this.ID = ID;
        this.data = data;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
