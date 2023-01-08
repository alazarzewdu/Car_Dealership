package com.example.car_dealership;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class edit_cars extends AppCompatActivity {
    private EditText careNameEdt,carPriceEdt,carModelEdt,carImageLinkEdt,carLinkEdt,carDescriptionEdt;
    private Button updateCar,deleteCar;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String CarID;
    private CarRVModal carRVModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cars);
        firebaseDatabase=FirebaseDatabase.getInstance();
        careNameEdt = findViewById(R.id.editCarName);
        carPriceEdt = findViewById(R.id.editCarPrice);
        carModelEdt = findViewById(R.id.editCarModel);
        careNameEdt = findViewById(R.id.editCarName);
        carImageLinkEdt = findViewById(R.id.editCarImage);
        carLinkEdt = findViewById(R.id.editCarLink);
        carDescriptionEdt = findViewById(R.id.editCarDescripton);
        updateCar = findViewById(R.id.update_car);
        deleteCar = findViewById(R.id.delete_car);
        loadingPB = findViewById(R.id.progress_bar);
        carRVModal =getIntent().getParcelableExtra("car");
        if (carRVModal!=null){
            careNameEdt.setText(carRVModal.getCarName());
            carPriceEdt.setText(carRVModal.getCarPrice());
            carDescriptionEdt.setText(carRVModal.getCarDescription());
            carModelEdt.setText(carRVModal.getCarModel());
            carImageLinkEdt.setText(carRVModal.getCarImage());
            carLinkEdt.setText(carRVModal.getCarLink());
            CarID = carRVModal.getCarID();
        }
        databaseReference =firebaseDatabase.getReference("Cars").child(CarID);
        updateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String carName = careNameEdt.getText().toString();
                String carModel = carModelEdt.getText().toString();
                String carPrice = carPriceEdt.getText().toString();
                String imgLink = carImageLinkEdt.getText().toString();
                String carLink = carLinkEdt.getText().toString();
                String carDescription = carDescriptionEdt.getText().toString();
                Map<String,Object> map = new HashMap<>();
                map.put("carName",carName);
                map.put("carPrice",carPrice);
                map.put("carDescription",carDescription);
                map.put("carModel",carModel);
                map.put("carImage",imgLink);
                map.put("carLink",carLink);
                map.put("carID",CarID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(edit_cars.this, "Car has been updated successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(edit_cars.this , MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(edit_cars.this, "Error is "+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        deleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();
            }
        });


    }
    private void deleteCourse(){
        databaseReference.removeValue();
        Toast.makeText(edit_cars.this, "Car has been DELETED", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(edit_cars.this , MainActivity.class));
    }
}