package co.androidninja.popularmovies.movieslist;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.androidninja.popularmovies.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TopRatedSelectedTest {

    @Rule
    public ActivityTestRule<MoviesActivity> mActivityTestRule = new ActivityTestRule<>(MoviesActivity.class);

    @Test
    public void topRatedSelectedTest() {
        ViewInteraction spinner = onView(
                allOf(withId(R.id.drop_down), isDisplayed()));
        spinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Top Rated"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerview), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.movie_title), withText("The Imitation Game"), isDisplayed()));
        textView.check(matches(withText("The Imitation Game")));

    }
}
