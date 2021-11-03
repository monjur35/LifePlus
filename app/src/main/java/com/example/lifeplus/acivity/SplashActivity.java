package com.example.lifeplus.acivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.example.lifeplus.R;
import com.example.lifeplus.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    Animation topAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_anim);





        binding.logo.setAnimation(topAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent authentication=new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(authentication);
                finish();
            }
        },2000);
    }
}