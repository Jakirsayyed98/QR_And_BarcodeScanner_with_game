package com.jhcreativedeveloper.BarcodeScanner.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyDataBase(
    @field:PrimaryKey(autoGenerate = true)
    var iD: Int,
    @field:ColumnInfo(name = "data")
    var data: String
)