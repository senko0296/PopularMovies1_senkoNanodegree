package com.example.anirudhraghunath.popularmovies1.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anirudhraghunath.popularmovies1.R;
import com.example.anirudhraghunath.popularmovies1.resources.Constants;
import com.example.anirudhraghunath.popularmovies1.utilities.Result;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    Result mMovieResults;
    ImageView mToolbarPosterImageView;
    CardView mOverviewCardView;
    TextView mTitleTextView, mReleaseDateTextView, mRatingsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mToolbarPosterImageView = (ImageView) findViewById(R.id.toolbar_poster_image_view);
        mOverviewCardView = (CardView) findViewById(R.id.overview_card_view);
        mTitleTextView = (TextView) findViewById(R.id.title_text_view);
        mReleaseDateTextView = (TextView) findViewById(R.id.release_date_text_view);
        mRatingsTextView = (TextView) findViewById(R.id.ratings_text_view);

        mMovieResults = (Result) getIntent().getSerializableExtra(getString(R.string.detailsExtra));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(mMovieResults.getTitle());

        Picasso.with(MovieDetailActivity.this).load(Constants.URL_POSTER_IMAGE + mMovieResults.getPosterPath())
                .placeholder(R.drawable.loading)
                .into(mToolbarPosterImageView);

        mTitleTextView.setText(mMovieResults.getTitle());
        mReleaseDateTextView.setText(mMovieResults.getReleaseDate());
        mRatingsTextView.setText(mMovieResults.getVoteAverage().toString() + "/10");
        mOverviewCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(MovieDetailActivity.this)
                        .setTitle(mMovieResults.getTitle())
                        .setMessage(mMovieResults.getOverview())
                        .setPositiveButton(getString(R.string.close), null)
                        .create().show();
            }
        });
    }
}
