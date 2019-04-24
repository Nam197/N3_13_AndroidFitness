package com.example.luongoc.n3_13_androidfitness.TrangChu;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.luongoc.n3_13_androidfitness.R;
import com.example.luongoc.n3_13_androidfitness.TrangChu.MainActivity;
import com.example.luongoc.n3_13_androidfitness.TrangChu.MainCustom;
import com.example.luongoc.n3_13_androidfitness.TrangChu.MainDangKi;
import com.example.luongoc.n3_13_androidfitness.TrangChu.MainMenu;

public class MainTrangChu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu);

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MainActivity()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigation= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;
            switch (item.getItemId()){
                case R.id.item1:
                    selectedFragment= new MainActivity();
                    Toast.makeText(MainTrangChu.this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item2 :
                    selectedFragment=new MainMenu();
                    Toast.makeText(MainTrangChu.this, "Menu", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item3 :
                    selectedFragment=new MainDangKi();
                    Toast.makeText(MainTrangChu.this, "Đăng Kí", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item4 :
                    selectedFragment=new MainCustom();
                    Toast.makeText(MainTrangChu.this, "Yêu Thích", Toast.LENGTH_SHORT).show();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
            return false;
        }
    };
}
