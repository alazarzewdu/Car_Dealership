package com.example.car_dealership;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarRVAdapter extends RecyclerView.Adapter<CarRVAdapter.ViewHolder> {
    private ArrayList<CarRVModal> carRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private CarClickInterface carClickInterface;

    @NonNull
    @Override
    public CarRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.car_rv_item,parent,false);
       return new ViewHolder(view);
    }

    public CarRVAdapter(ArrayList<CarRVModal> carRVModalArrayList, Context context, CarClickInterface carClickInterface) {
        this.carRVModalArrayList = carRVModalArrayList;
        this.context = context;
        this.carClickInterface = carClickInterface;
    }

    @Override
    public void onBindViewHolder(@NonNull CarRVAdapter.ViewHolder holder, int position) {
        CarRVModal carRVModal = carRVModalArrayList.get(position);
        holder.carNameTV.setText(carRVModal.getCarName());
        holder.carPriceTV.setText("Rs."+ carRVModal.getCarPrice());
        Picasso.get().load(carRVModal.getCarImage()).into(holder.carIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carClickInterface.onCarClick(position);
            }
        });

    }
    private void setAnimation(View itemView,int position){
        if (position>lastPos){
            Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return carRVModalArrayList.size();
    }
    public class ViewHolder  extends RecyclerView.ViewHolder{
        private TextView carNameTV,carPriceTV;
        private ImageView carIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carNameTV = itemView.findViewById(R.id.idTVName);
            carPriceTV = itemView.findViewById(R.id.idTVPrice);
            carIV = itemView.findViewById(R.id.idIVCar);
        }
    }
    public interface CarClickInterface{
        void onCarClick(int position);

    }
}
