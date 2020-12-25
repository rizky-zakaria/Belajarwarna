package com.gorontalo.laundryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gorontalo.laundryapp.Api.APIRequestData;
import com.gorontalo.laundryapp.Adapter.AdapterData;
import com.gorontalo.laundryapp.Api.RetroServer;
import com.gorontalo.laundryapp.Model.DataModel;
import com.gorontalo.laundryapp.Model.ResponseModel;
import com.gorontalo.laundryapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pbData = findViewById(R.id.pb_data);
        srlData = findViewById(R.id.slr_data);

        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

//        retrieveData();

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
    }

//    function tampil adapter data
    public void retrieveData(){
        pbData.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String status = response.body().getStatus();

                Toast.makeText(MainActivity.this, "Status : " + status, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adData = new AdapterData(MainActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}
