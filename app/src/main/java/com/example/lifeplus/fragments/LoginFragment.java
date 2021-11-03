package com.example.lifeplus.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lifeplus.R;
import com.example.lifeplus.acivity.HomeActivity;
import com.example.lifeplus.databinding.FragmentLoginBinding;
import com.example.lifeplus.models.UserModel;
import com.example.lifeplus.utils.StoreDataPreference;
import com.example.lifeplus.viewmodel.AuthViewModel;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private AuthViewModel authViewModel;
    private StoreDataPreference storeDataPreference;

    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentLoginBinding.inflate(inflater);
        authViewModel=new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        storeDataPreference=StoreDataPreference.getInstance(requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (storeDataPreference.getLogInStatus()){
            homeInten();
        }


        Handler handler=new Handler(Looper.getMainLooper());


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=binding.userName.getText().toString();
                String pass=binding.pass.getText().toString();

                if (userName.isEmpty() ||pass.isEmpty()){
                    Toast.makeText(requireContext(), "Please ,Verify all field", Toast.LENGTH_SHORT).show();
                }
                else {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            authViewModel.login(userName,pass).observe(getViewLifecycleOwner(), new Observer<UserModel>() {
                                @Override
                                public void onChanged(UserModel userModel) {

                                    if (userModel==null){
                                        Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                       homeIntent();
                                    }
                                }
                            });
                        }
                    });

                }
            }
        });

        binding.registrationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
            }
        });
    }

    private void homeIntent() {
        Intent home=new Intent(getActivity(), HomeActivity.class);
        startActivity(home);
        getActivity().finish();
    }
}