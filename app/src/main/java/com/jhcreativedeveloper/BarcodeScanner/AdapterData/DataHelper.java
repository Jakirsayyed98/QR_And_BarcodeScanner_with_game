package com.jhcreativedeveloper.BarcodeScanner.AdapterData;

public class DataHelper {
    String data,ID;

    public DataHelper(String data, String ID) {
        this.data = data;
        this.ID = ID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
