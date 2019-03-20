package jc.highapp.githubrepositorysearch.main

import jc.highapp.githubrepositorysearch.navigation.Navigator

class MainRouter(private val navigator: Navigator) {

    fun startFlow() {
        navigator.clearFragmentsStack()
        navigator.showEventsFragment()
    }

    fun showEventsScreen() {
        navigator.showEventsFragment()
    }

    fun showRepositoriesScreen() {
        navigator.showRepositoriesFragment()
    }

    fun showUsersScreen() {
        navigator.showUsersFragment()
    }

}
