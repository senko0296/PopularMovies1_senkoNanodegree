package com.example.anirudhraghunath.popularmovies1.network;

import com.example.anirudhraghunath.popularmovies1.resources.Constants;
import com.example.anirudhraghunath.popularmovies1.utilities.Movies;



import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
/**
 * Created by anirudhraghunath on 31/01/16.
 */
public class APIClient {

    public static tmdbInterface movietmdbInterface = null;

    public static tmdbInterface getApi(){

        if(movietmdbInterface == null){

            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.URL_BASE)
                    .build();
            movietmdbInterface = adapter.create(tmdbInterface.class);
        }
        return movietmdbInterface;
    }

    public interface tmdbInterface {

        @GET("/discover/movie?sort_by=popularity.desc&api_key=" + Constants.API_KEY)
        public void getPopular(Callback<Movies> moviesCallback);

        @GET("/discover/movie?sort_by=vote_average.desc&api_key=" + Constants.API_KEY)
        public void getTopRated(Callback<Movies> moviesCallback);
    }
}
