package com.gorontalo.laundryapp.Api;

import com.gorontalo.laundryapp.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {

    @GET("index_get")
    Call<ResponseModel> ardRetrieveData();

}
