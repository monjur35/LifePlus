package com.example.lifeplus.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifeplus.R;
import com.example.lifeplus.adapter.MovieAdapter;
import com.example.lifeplus.databinding.FragmentDashboardBinding;
import com.example.lifeplus.models.UserModel;
import com.example.lifeplus.reponse.shows.ShowsResponse;
import com.example.lifeplus.viewmodel.AuthViewModel;
import com.example.lifeplus.viewmodel.DashViewModel;
import com.example.lifeplus.viewmodel.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashViewModel dashViewModel;
    private int page=0,limit=2000;
    private List<ShowsResponse>showsList;
    private MovieAdapter adapter;
    private LinearLayoutManager linearLayoutManager;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDashboardBinding.inflate(inflater);
        dashViewModel=new ViewModelProvider(requireActivity()).get(DashViewModel.class);

        showsList=new ArrayList<>();
        adapter=new MovieAdapter (requireContext(),showsList);
        linearLayoutManager=new LinearLayoutManager(requireContext());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Handler handler=new Handler(Looper.getMainLooper());

        binding.dashRV.setAdapter(adapter);
        binding.dashRV.setLayoutManager(linearLayoutManager);

        handler.post(new Runnable() {
            @Override
            public void run() {
                callForShows(page);

                binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                            if (page < limit) {
                                page++;
                                Log.d("TAG", "onScrollChange: " + page + "  " + limit);

                                binding.spinKit.setVisibility(View.VISIBLE);
                                callForShows(page);
                            }

                        }
                    }
                });
            }
        });

        binding.profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dashboardFragment_to_profileFragment);
            }
        });






    }

    private void callForShows(int page) {
        dashViewModel.getAllShow(page).observe(getViewLifecycleOwner(), new Observer<List<ShowsResponse>>() {
            @Override
            public void onChanged(List<ShowsResponse> showsResponses) {
                if (showsResponses!=null){
                    binding.spinKit.setVisibility(View.GONE);
                    showsList.addAll(showsResponses);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }




}