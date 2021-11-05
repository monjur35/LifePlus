package com.example.lifeplus.acivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lifeplus.R;
import com.example.lifeplus.utils.StoreDataPreference;

public class HomeActivity extends AppCompatActivity {
    private StoreDataPreference storeDataPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeDataPreference=StoreDataPreference.getInstance(this);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onStop() {
        super.onStop();
        storeDataPreference.setSearchKey("");
    }
}