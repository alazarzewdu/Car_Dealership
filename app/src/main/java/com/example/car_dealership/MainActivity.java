package com.example.car_dealership;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CarRVAdapter.CarClickInterface {
    private RecyclerView carRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<CarRVModal> carRVModalArrayList;
    private RelativeLayout bottomRl;
    private CarRVAdapter carRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carRV = findViewById(R.id.idRVCars);
        loadingPB = findViewById(R.id.id_progress_bar);
        addFAB = findViewById(R.id.FAB);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Cars");
        carRVModalArrayList = new ArrayList<>();
        bottomRl = findViewById(R.id.car_info_card);
        carRVAdapter = new CarRVAdapter(carRVModalArrayList,this,this);
        carRV.setLayoutManager(new LinearLayoutManager(this));
        carRV.setAdapter(carRVAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,add_cars.class));
            }
        });
    }

    private void getAllCars(){
        carRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                carRVModalArrayList.add(snapshot.getValue(CarRVModal.class));
                carRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                carRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                carRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                carRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCarClick(int position) {
        displayBottomSheet(carRVModalArrayList.get(position));

    }
    private void displayBottomSheet(CarRVModal carRVModal){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog,bottomRl);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView carNameTV = layout.findViewById(R.id.car_name);
        TextView carDesTV = layout.findViewById(R.id.car_description);
        TextView carModelTV = layout.findViewById(R.id.car_model);
        TextView carPriceTV = layout.findViewById(R.id.car_price);
        ImageView carIV = layout.findViewById(R.id.car_image);
        Button editBtn = findViewById(R.id.edit_car);
        Button view_car_detailsBtn = findViewById(R.id.view_car_details);

        carNameTV.setText(carRVModal.getCarName());
        carDesTV.setText(carRVModal.getCarDescription());
        carModelTV.setText(carRVModal.getCarModel());
        carPriceTV.setText(carRVModal.getCarPrice());
        Picasso.get().load(carRVModal.getCarImage()).into(carIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(MainActivity.this,edit_cars.class);
                i.putExtra("car", (CharSequence) carRVModal);
            }
        });

        view_car_detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(carRVModal.getCarLink()));
                startActivity(i);
            }
        });
    }
}