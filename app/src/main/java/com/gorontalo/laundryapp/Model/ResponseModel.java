package com.gorontalo.laundryapp.Model;

import java.util.List;

public class ResponseModel {

    private String status;
    private List<DataModel> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }
}
