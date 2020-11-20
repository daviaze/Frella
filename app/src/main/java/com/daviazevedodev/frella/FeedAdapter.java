package com.daviazevedodev.frella;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.daviazevedodev.frella.Model.Categoria;

import org.w3c.dom.Text;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

        private Context mContext;
        private List<Categoria> mData;

    public FeedAdapter(Context mContext, List<Categoria> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater  = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_feed, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tx_gategoria_title.setText(mData.get(position).getTitle());
        holder.categoria_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.card_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Service_Activity.class);
                intent.putExtra("title", mData.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tx_gategoria_title;
        ImageView categoria_thumbnail;
        CardView card_feed;
        public MyViewHolder( View itemView) {
            super(itemView);

            tx_gategoria_title = (TextView) itemView.findViewById(R.id.text_card_feed) ;
            categoria_thumbnail = (ImageView) itemView.findViewById(R.id.img_card_feed);
            card_feed = (CardView) itemView.findViewById(R.id.card_feed);



        }
    }



}
