package jc.highapp.githubrepositorysearch.navigation

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.repositories.view.RepositoriesFragment
import jc.highapp.githubrepositorysearch.users.EventsFragment
import jc.highapp.githubrepositorysearch.users.UsersFragment
import jc.highapp.githubrepositorysearch.utils.sendAction
import jc.highapp.githubrepositorysearch.utils.showFragment
import kotlinx.android.synthetic.main.activity_main.view.*


class Navigator {

    private var activity : FragmentActivity? = null

    fun bindRoot(activity : FragmentActivity?) {
        this.activity = activity
    }

    fun unbindRoot() {
        this.activity = null
    }

    fun showRepositoriesFragment() {
        activity?.showFragment(RepositoriesFragment(), R.id.fragment_container)
    }

    fun showPageInBrowser(repositoryUrl: String) {
        activity?.sendAction(Intent.ACTION_VIEW, Uri.parse(repositoryUrl))
    }

    fun clearFragmentsStack() {

    }

    fun showEventsFragment() {
        activity?.showFragment(EventsFragment(), R.id.fragment_container)

    }

    fun showUsersFragment() {
        activity?.showFragment(UsersFragment(), R.id.fragment_container)
    }

}