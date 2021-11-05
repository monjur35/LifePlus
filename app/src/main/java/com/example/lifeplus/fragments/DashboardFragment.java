package com.example.lifeplus.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.lifeplus.R;
import com.example.lifeplus.adapter.MovieAdapter;
import com.example.lifeplus.databinding.FragmentDashboardBinding;
import com.example.lifeplus.reponse.shows.search.SearchResponse;
import com.example.lifeplus.reponse.shows.single.ShowsResponse;
import com.example.lifeplus.utils.StoreDataPreference;
import com.example.lifeplus.viewmodel.DashViewModel;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashViewModel dashViewModel;
    private int page=0,limit=2000;
    private List<SearchResponse>showsList;
    private MovieAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private StoreDataPreference storeDataPreference;

    String search;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDashboardBinding.inflate(inflater);
        dashViewModel=new ViewModelProvider(requireActivity()).get(DashViewModel.class);
        storeDataPreference=StoreDataPreference.getInstance(requireContext());

        showsList=new ArrayList<>();

        linearLayoutManager=new LinearLayoutManager(requireContext());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Handler handler=new Handler(Looper.getMainLooper());


        search =storeDataPreference.getKeySearch();
        callForShows(search);


        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s=binding.searchBox.getText().toString();
                storeDataPreference.setSearchKey(s);
                if (!s.isEmpty()){
                    binding.spinKit.setVisibility(View.VISIBLE);
                    hideKeyBoard(view);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callForShows(s);

                        }
                    });
                }
                else {
                    callForShows(search);
                    Toast.makeText(requireActivity(), "Input something first", Toast.LENGTH_SHORT).show();
                }
            }
        });



        binding.profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dashboardFragment_to_profileFragment);
            }
        });






    }

    private void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager)requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void callForShows(String s) {
        dashViewModel.getAllShow(s).observe(getViewLifecycleOwner(), new Observer<List<SearchResponse>>() {
            @Override
            public void onChanged(List<SearchResponse> showsResponses) {
                if (showsResponses!=null){

                    adapter=new MovieAdapter (requireContext(),showsResponses);
                    binding.dashRV.setAdapter(adapter);
                    binding.dashRV.setLayoutManager(linearLayoutManager);
                    adapter.notifyDataSetChanged();
                    binding.spinKit.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}