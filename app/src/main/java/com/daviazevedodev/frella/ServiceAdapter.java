package com.daviazevedodev.frella;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.daviazevedodev.frella.Model.Service;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class ServiceAdapter extends FirebaseRecyclerAdapter<Service, ServiceAdapter.myviewholder> {
    public ServiceAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_service, parent, false);
        return new myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ServiceAdapter.myviewholder holder, int position, @NonNull Service model) {
        holder.service.setText(model.getName_service());
        holder.name.setText(model.getName_person());
        holder.works.setText(model.getPortifolio());
        holder.tell.setText(model.getTelephone());
        holder.description.setText(model.getDescription());


    }

    class myviewholder extends RecyclerView.ViewHolder {

          TextView service, name, works, tell, description, area;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            service = (TextView) itemView.findViewById(R.id.textView);
            name = (TextView) itemView.findViewById(R.id.textView2);
            works = (TextView) itemView.findViewById(R.id.textView4);
            tell = (TextView) itemView.findViewById(R.id.textView6);
            description = (TextView) itemView.findViewById(R.id.textView3);
        }
    }
}
