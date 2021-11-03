package com.example.lifeplus.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeplus.R;
import com.example.lifeplus.reponse.shows.search.SearchResponse;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context context;
    private List<SearchResponse>showsList;

    public MovieAdapter(Context context, List<SearchResponse> showsList) {
        this.context = context;
        this.showsList = showsList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View items= LayoutInflater.from(context).inflate(R.layout.show_row,null,false);
        return new MovieViewHolder(items);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        SearchResponse searchResponse=showsList.get(position);

        if (searchResponse.getShow().getGenres()!=null){
           String genre= Arrays.toString(searchResponse.getShow().getGenres().toArray());
           holder.genre.setText(genre);
        }

        if (searchResponse.getShow().getImage()!=null){
            Picasso.get().load(searchResponse.getShow().getImage().getOriginal()).into(holder.poster);
        }

        holder.movieName.setText(searchResponse.getShow().getName());
        holder.year.setText("("+searchResponse.getShow().getPremiered()+")");
        if (searchResponse.getShow().getRating()!=null){
            holder.imdb.setText("IMDB : "+searchResponse.getShow().getRating().getAverage()+"/10");
        }

        if (searchResponse.getShow().getRuntime()!=null){
            holder.duration.setText(searchResponse.getShow().getRuntime().toString() + " mins");
        }



        Bundle bundle=new Bundle();
        bundle.putString("id",searchResponse.getShow().getId().toString());

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dashboardFragment_to_detailsFragment,bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView movieName,year,imdb,duration,genre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.imageinRow);
            movieName=itemView.findViewById(R.id.movieNameinRow);
            year=itemView.findViewById(R.id.yearinRow);
            imdb=itemView.findViewById(R.id.rating);
            duration=itemView.findViewById(R.id.duration);
            genre=itemView.findViewById(R.id.gnre);

        }
    }
}
