package com.gorontalo.laundryapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gorontalo.laundryapp.Model.DataModel;
import com.gorontalo.laundryapp.R;

import java.io.IOException;
import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context context;
    private List<DataModel> listLaundry;

    public AdapterData(Context ctx, List<DataModel> listLaundry){
        this.context = ctx;
        this.listLaundry = listLaundry;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listLaundry.get(position);

        holder.tvId.setText(String.valueOf(dm.getId_warna()));
        holder.tvNama.setText(dm.getWarna());
        holder.tvJudul.setText(dm.getSuara());
        holder.warna.setBackgroundColor(Color.parseColor(dm.getKode_warna()));
        holder.warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url =  dm.getSuara();
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(context, Uri.parse(url));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                Toast.makeText(context, dm.getWarna(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLaundry.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvNama, tvId, tvJudul;
        Button warna;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.warna);
            tvNama = itemView.findViewById(R.id.link);
            tvId = itemView.findViewById(R.id.tv_id_pinjam);
            warna = itemView.findViewById(R.id.button);
        }
    }
}
