package com.example.anirudhraghunath.popularmovies1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anirudhraghunath.popularmovies1.R;
import com.example.anirudhraghunath.popularmovies1.adapters.DashBoardGridAdapter;
import com.example.anirudhraghunath.popularmovies1.network.APIClient;
import com.example.anirudhraghunath.popularmovies1.utilities.Movies;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DashBoardActivity extends AppCompatActivity {

    GridView moviesGridView;
    ProgressDialog pgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        moviesGridView = (GridView) findViewById(R.id.movies_grid_view);
        pgDialog = new ProgressDialog(DashBoardActivity.this);
        pgDialog.setMessage(getString(R.string.fetchingData));
        pgDialog.show();
        setTitle(getString(R.string.popularMovies));
        getPopular();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_popular:
                getPopular();
                return true;
            case R.id.action_top_rated:
                getTopRated();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void getPopular() {

        APIClient.getApi().getPopular(new Callback<Movies>() {
            @Override
            public void success(final Movies movies, Response response) {

                setTitle(getString(R.string.popularMovies));
                pgDialog.hide();
                moviesGridView.setAdapter(new DashBoardGridAdapter(getApplicationContext(), movies.getResults()));
                moviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        startActivity(new Intent(DashBoardActivity.this, MovieDetailActivity.class)
                                .putExtra(getString(R.string.detailsExtra), movies.getResults().get(i)));
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

                pgDialog.hide();
                Toast.makeText(getApplicationContext(), R.string.connectionFailed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getTopRated() {

        APIClient.getApi().getTopRated(new Callback<Movies>() {
            @Override
            public void success(final Movies movies, Response response) {

                setTitle(getString(R.string.topRatedMovies));
                pgDialog.hide();
                moviesGridView.setAdapter(new DashBoardGridAdapter(getApplicationContext(), movies.getResults()));
                moviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        startActivity(new Intent(DashBoardActivity.this, MovieDetailActivity.class)
                                .putExtra(getString(R.string.detailsExtra), movies.getResults().get(i)));
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

                pgDialog.hide();
                Toast.makeText(getApplicationContext(), R.string.connectionFailed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
