package com.example.lifeplus.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifeplus.R;
import com.example.lifeplus.databinding.FragmentProfileBinding;
import com.example.lifeplus.models.UserModel;
import com.example.lifeplus.utils.StoreDataPreference;
import com.example.lifeplus.viewmodel.ProfileViewModel;


public class ProfileFragment extends Fragment {

    private StoreDataPreference storeDataPreference;
    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;



    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentProfileBinding.inflate(inflater);
        storeDataPreference=StoreDataPreference.getInstance(requireContext());
        profileViewModel=new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String username=storeDataPreference.getKeyUserName();

        Handler handler =new Handler(Looper.getMainLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                profileViewModel.getUserData(username).observe(getViewLifecycleOwner(), new Observer<UserModel>() {
                    @Override
                    public void onChanged(UserModel userModel) {
                        if (userModel!=null){
                            binding.nameTV.setText(userModel.getName());
                            binding.name2.setText(userModel.getName());
                            binding.userName.setText(userModel.getUsername());
                            binding.phoneNumber.setText(userModel.getPhone());
                        }
                    }
                });
            }
        });


    }
}