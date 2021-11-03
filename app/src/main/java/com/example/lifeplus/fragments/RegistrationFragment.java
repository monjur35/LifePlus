package com.example.lifeplus.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lifeplus.acivity.HomeActivity;
import com.example.lifeplus.databinding.FragmentRegistrationBinding;
import com.example.lifeplus.models.UserModel;
import com.example.lifeplus.viewmodel.AuthViewModel;


public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding binding;
    private AuthViewModel authViewModel;


    public RegistrationFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentRegistrationBinding.inflate(inflater);
        authViewModel=new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Handler handler=new Handler(Looper.getMainLooper());

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=binding.userNamee.getText().toString();
                String name=binding.name.getText().toString();
                String phone=binding.phone.getText().toString();
                String pass=binding.pass.getText().toString();

                if (userName.isEmpty() || name.isEmpty()||phone.isEmpty() ||pass.isEmpty()){
                    Toast.makeText(requireContext(), "Please ,Verify all field", Toast.LENGTH_SHORT).show();
                }
                else {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            UserModel userModel=new UserModel(userName,name,phone,pass);
                            authViewModel.registerUser(userModel);
                            Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                            Intent home=new Intent(getActivity(), HomeActivity.class);
                            startActivity(home);
                        }
                    });

                }


                //getActivity().finish();
            }
        });
    }
}