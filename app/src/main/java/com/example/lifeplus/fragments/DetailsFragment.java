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
import android.view.WindowManager;

import com.example.lifeplus.databinding.FragmentDetailsBinding;
import com.example.lifeplus.reponse.shows.single.ShowsResponse;
import com.example.lifeplus.viewmodel.DashViewModel;
import com.squareup.picasso.Picasso;

import java.util.Arrays;


public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private DashViewModel dashViewModel;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding = FragmentDetailsBinding.inflate(inflater);
        dashViewModel=new ViewModelProvider(requireActivity()).get(DashViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Handler handler=new Handler(Looper.getMainLooper());

        Bundle bundle=getArguments();
        String id=bundle.getString("id");

        handler.post(new Runnable() {
            @Override
            public void run() {
                dashViewModel.getShowById(id).observe(getViewLifecycleOwner(), new Observer<ShowsResponse>() {
                    @Override
                    public void onChanged(ShowsResponse showsResponse) {
                        if (showsResponse!=null){

                            if (showsResponse.getImage()!=null){
                                Picasso.get().load(showsResponse.getImage().getOriginal()).into(binding.imageinRow);
                            }

                            binding.movieNameinRow.setText(showsResponse.getName());
                            binding.yearinRow.setText("("+showsResponse.getPremiered()+")");
                            binding.rating.setText("Imdb : "+showsResponse.getRating().getAverage()+"/10");
                            String genre= Arrays.toString(showsResponse.getGenres().toArray());
                            binding.gnre.setText(genre);
                            if (showsResponse.getRuntime()!=null){
                                binding.duration.setText("Duration : "+showsResponse.getRuntime().toString()+" mins");
                            }


                            if(showsResponse.getNetwork()!=null){
                                binding.country.setText(showsResponse.getNetwork().getCountry().getName());
                            }

                            binding.summaryText.setText(showsResponse.getSummary());
                            binding.time.setText(showsResponse.getSchedule().getTime());
                            String days=Arrays.toString(showsResponse.getSchedule().getDays().toArray());
                            binding.day.setText(days);

                            binding.spinKit.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}