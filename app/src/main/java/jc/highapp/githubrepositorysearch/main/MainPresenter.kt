package jc.highapp.githubrepositorysearch.main

class MainPresenter(private val mainRouter : MainRouter) {

    private var view : MainView? = null

    fun bindView(view: MainView) {
        this.view = view
    }

    fun unbindView() {
        this.view = null
    }

    fun onInit(isStateBeingRestored : Boolean) {
        if(!isStateBeingRestored) {
            mainRouter.startFlow()
        }

        view?.initBottomNavigation()
    }

    fun onEventsClick() {
        mainRouter.showEventsScreen()
    }

    fun onRepositoriesClick() {
        mainRouter.showRepositoriesScreen()
    }

    fun onUsersClick() {
        mainRouter.showUsersScreen()
    }
}