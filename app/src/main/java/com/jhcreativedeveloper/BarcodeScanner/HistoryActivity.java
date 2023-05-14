package com.jhcreativedeveloper.BarcodeScanner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.jhcreativedeveloper.BarcodeScanner.AdapterData.MyAdapter;
import com.jhcreativedeveloper.BarcodeScanner.RoomDB.AppDatabase;
import com.jhcreativedeveloper.BarcodeScanner.RoomDB.MyDataBase;
import com.jhcreativedeveloper.BarcodeScanner.RoomDB.UserDao;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView scan_RV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FFFFFF"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        scan_RV=findViewById(R.id.Scan_RV);
        getRoomData();
    }
    private void getRoomData() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database_room").allowMainThreadQueries().build();

        UserDao userDao=db.userDao();
        scan_RV.setLayoutManager(new LinearLayoutManager(this));
        List<MyDataBase> dataBases=userDao.getAllData();
        MyAdapter adapter=new MyAdapter(this,dataBases);
        scan_RV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}