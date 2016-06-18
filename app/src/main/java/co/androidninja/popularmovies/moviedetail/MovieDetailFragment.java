package co.androidninja.popularmovies.moviedetail;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;
import co.androidninja.popularmovies.databinding.FragmentMovieDetailBinding;


public class MovieDetailFragment extends Fragment {

    private static final String MOVIE = "movie";

    private Movie mMovie;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param movie This is the {@link Movie} whose details you wish to see
     * @return A new instance of fragment MovieDetailFragment.
     */
    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMovieDetailBinding dataBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_detail, container, false);
        View view = dataBinding.getRoot();
        dataBinding.setMovie(mMovie);
        return view;
    }

}
