package co.androidninja.popularmovies.movieslist;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.androidninja.popularmovies.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MoviesListTest {

    @Rule
    public ActivityTestRule<MoviesActivity> mMoviesActivity =
            new ActivityTestRule<>(MoviesActivity.class);


//    @Test
//    public void checkSpinnerInitialPosition() throws Exception {
//        onView(withId(R.id.drop_down)).check(matches(withText(R.string.popular_movies)));
//        onView(withId(R.id.drop_down)).perform(click());
//    }

    @Test
    public void clickOnItemInRecyclerView() throws Exception {
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToPosition(2))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.movie_title)).check(matches(withText("Jurassic World")));
    }
}
