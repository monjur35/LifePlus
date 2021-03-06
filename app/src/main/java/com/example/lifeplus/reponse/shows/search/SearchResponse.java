
package com.example.lifeplus.reponse.shows.search;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SearchResponse {

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("show")
    @Expose
    private Show show;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

}
