package jc.highapp.githubrepositorysearch.users

import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.main.BaseFragment
import org.kodein.di.generic.instance

class UsersFragment : BaseFragment<UsersPresenter, UsersView>() {
    override val layoutResId: Int = R.layout.users_fragment_layout
    override val presenter: UsersPresenter by instance()
}