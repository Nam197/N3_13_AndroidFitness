package com.example.luongoc.n3_13_androidfitness.Main2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.luongoc.n3_13_androidfitness.Main3CoBung.Main2CoBungBicycle;
import com.example.luongoc.n3_13_androidfitness.Main3CoBung.Main2CoBungHanging;
import com.example.luongoc.n3_13_androidfitness.Main3CoBung.Main2CoBungRoman;
import com.example.luongoc.n3_13_androidfitness.R;
import com.example.luongoc.n3_13_androidfitness.TrangChu.MainTrangChu;

public class Main2CoBung extends AppCompatActivity {
    private LinearLayout llAbs1, llAbs2, llAbs3;
    private ImageView imgBackMain;
    private ImageView imgAvt1, imgAvt2, imgAvt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_co_bung);
        imgAvt1= findViewById(R.id.imgAvt1);
        imgAvt2= findViewById(R.id.imgAvt2);
        imgAvt3=findViewById(R.id.imgAvt3);
        llAbs1=findViewById(R.id.llAbs1);
        llAbs2=findViewById(R.id.llAbs2);
        llAbs3= findViewById(R.id.llAbs3);
        imgBackMain=findViewById(R.id.imgBackMain);
        imgBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main2CoBung.this, MainTrangChu.class);
                startActivity(intent);
            }
        });


        llAbs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main2CoBung.this, Main2CoBungBicycle.class);
                startActivity(intent);
            }
        });
        llAbs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main2CoBung.this, Main2CoBungHanging.class);
                startActivity(intent);
            }
        });

        llAbs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main2CoBung.this, Main2CoBungRoman.class);
                startActivity(intent);
            }
        });

        //Hàm Bo ảnh
        BoAnh();
    }
    //Bo ảnh
    private void BoAnh(){
        Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.drawable.hot);
        RoundedBitmapDrawable roundedBitmapDrawable1= RoundedBitmapDrawableFactory.create(getResources(), bitmap1);
        roundedBitmapDrawable1.setCircular(true);
        imgAvt1.setImageDrawable(roundedBitmapDrawable1);

        Bitmap bitmap2= BitmapFactory.decodeResource(getResources(), R.drawable.hot);
        RoundedBitmapDrawable roundedBitmapDrawable2= RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
        roundedBitmapDrawable2.setCircular(true);
        imgAvt2.setImageDrawable(roundedBitmapDrawable2);

        Bitmap bitmap3= BitmapFactory.decodeResource(getResources(), R.drawable.hot);
        RoundedBitmapDrawable roundedBitmapDrawable3= RoundedBitmapDrawableFactory.create(getResources(), bitmap3);
        roundedBitmapDrawable3.setCircular(true);
        imgAvt3.setImageDrawable(roundedBitmapDrawable3);
    }
}
