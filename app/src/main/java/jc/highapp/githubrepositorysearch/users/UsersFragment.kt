package jc.highapp.githubrepositorysearch.users

import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.events.presenter.EventsPresenter
import jc.highapp.githubrepositorysearch.events.view.EventsView
import jc.highapp.githubrepositorysearch.mvp.BaseFragment
import org.kodein.di.generic.instance

class UsersFragment : BaseFragment<EventsPresenter, EventsView>() {
    override val layoutResId: Int = R.layout.users_fragment_layout
    override val presenter: EventsPresenter by instance()
}