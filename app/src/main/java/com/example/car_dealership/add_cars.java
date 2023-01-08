package com.example.car_dealership;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_cars extends AppCompatActivity {
    private EditText careNameEdt,carPriceEdt,carModelEdt,carImageLinkEdt,carLinkEdt,carDescriptionEdt;
    private Button addCar;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String CarID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);
        careNameEdt = findViewById(R.id.editCarName);
        carPriceEdt = findViewById(R.id.editCarPrice);
        carModelEdt = findViewById(R.id.editCarModel);
        careNameEdt = findViewById(R.id.editCarName);
        carImageLinkEdt = findViewById(R.id.editCarImage);
        carLinkEdt = findViewById(R.id.editCarLink);
        carDescriptionEdt = findViewById(R.id.editCarDescripton);
        addCar = findViewById(R.id.AddCar);
        loadingPB = findViewById(R.id.progress_bar);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference("Cars");

        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carName = careNameEdt.getText().toString();
                String carSuitedFor = carModelEdt.getText().toString();
                String carPrice = carPriceEdt.getText().toString();
                String imgLink = carImageLinkEdt.getText().toString();
                String carLink = carLinkEdt.getText().toString();
                String carDescription = carDescriptionEdt.getText().toString();
                CarID = carName;
            }
        });
    }
}