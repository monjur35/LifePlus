package com.example.lifeplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeplus.R;
import com.example.lifeplus.reponse.shows.ShowsResponse;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context context;
    private List<ShowsResponse>showsList;

    public MovieAdapter(Context context, List<ShowsResponse> showsList) {
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
        ShowsResponse showsResponse=showsList.get(position);

        String genre= Arrays.toString(showsResponse.getGenres().toArray());
        Picasso.get().load(showsResponse.getImage().getMedium()).into(holder.poster);
        holder.movieName.setText(showsResponse.getName());
        holder.year.setText("("+showsResponse.getPremiered()+")");
        holder.imdb.setText("IMDB : "+showsResponse.getRating().getAverage()+"/10");
        if (showsResponse.getRuntime()!=null){
            holder.duration.setText(showsResponse.getRuntime().toString() + " mins");
        }

        holder.genre.setText(genre);


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
