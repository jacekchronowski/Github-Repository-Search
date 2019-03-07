package jc.highapp.githubrepositorysearch.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import jc.highapp.githubrepositorysearch.search.view.SearchFragment
import jc.highapp.githubrepositorysearch.utils.sendAction
import jc.highapp.githubrepositorysearch.utils.showFragment


class Navigator {

    private var activity : FragmentActivity? = null

    fun bindRoot(activity : FragmentActivity?) {
        this.activity = activity
    }

    fun unbindRoot() {
        this.activity = null
    }

    fun showSearchFragment() {
        activity?.showFragment(SearchFragment())
    }

    fun startFlow() {
        showSearchFragment()
    }

    fun showPageInBrowser(repositoryUrl: String) {
        activity?.sendAction(Intent.ACTION_VIEW, Uri.parse(repositoryUrl))
    }

}