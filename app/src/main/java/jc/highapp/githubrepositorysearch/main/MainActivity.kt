package jc.highapp.githubrepositorysearch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.navigation.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware, MainView {

    override val kodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }
    private val presenter : MainPresenter by instance()

    private val navigator : Navigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bindView(this)
        navigator.bindRoot(this)
        presenter.onInit(savedInstanceState != null)

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
        navigator.unbindRoot()
    }

    override fun initBottomNavigation() {

        bottom_navigation_bar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_action_events -> presenter.onEventsClick()
                R.id.menu_action_repositories -> presenter.onRepositoriesClick()
                R.id.menu_action_users -> presenter.onUsersClick()
            }
            true
        }
    }
}
