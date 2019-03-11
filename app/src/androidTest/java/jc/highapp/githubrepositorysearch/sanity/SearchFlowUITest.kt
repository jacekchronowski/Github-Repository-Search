package jc.highapp.githubrepositorysearch.sanity

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.assertions.RecyclerViewItemCountAssertion.Companion.withItemCount
import jc.highapp.githubrepositorysearch.main.MainActivity
import jc.highapp.githubrepositorysearch.search.adapter.RepositoryViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchFlowUITest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLoadingPaginationNextPage() {
        Espresso.onView(withId(R.id.et_search)).perform(ViewActions.typeTextIntoFocusedView("jQuery"))
        Espresso.onView(withId(R.id.rv_search_results)).check(withItemCount(30))

        Espresso.onView(withId(R.id.rv_search_results)).perform(RecyclerViewActions.scrollToPosition<RepositoryViewHolder>(26))

        Espresso.onView(withId(R.id.rv_search_results)).check(withItemCount(60))
    }
}