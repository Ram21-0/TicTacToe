package com.example.tictactoe;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String[] a;
    private Context context;
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public MyAdapter(String[] a, Context context) {
        this.a = a;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.box_card,parent,false);
        return new MyViewHolder(v,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String s = a[position];
        Uri img;

        if(s.equals(" ")) img = Uri.parse("android.resource://com.example.tictactoe/drawable/mixcloud");
        else img = s.equals("X") ? PlayerInfo.img1 : PlayerInfo.img2;

        Picasso.with(context).load(img).centerCrop().resize(350,350).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.boxPic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                            onItemClickListener.onItemClick(pos);
                    }
                }
            });
        }
    }
}
