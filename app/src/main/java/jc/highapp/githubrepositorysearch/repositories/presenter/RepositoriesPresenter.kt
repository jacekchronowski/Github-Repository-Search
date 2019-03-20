package jc.highapp.githubrepositorysearch.repositories.presenter

import io.reactivex.Observable
import jc.highapp.githubrepositorysearch.main.BasePresenter
import jc.highapp.githubrepositorysearch.repositories.interactor.SearchInteractor
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.repositories.router.RepositoriesRouter
import jc.highapp.githubrepositorysearch.repositories.view.RepositoriesView
import jc.highapp.githubrepositorysearch.utils.addTo

class RepositoriesPresenter(
    private val searchInteractor : SearchInteractor,
    private val repositoriesRouter: RepositoriesRouter) : BasePresenter<RepositoriesView>(){


    override fun onInit() {
        view?.initList()
    }

    fun onRepositoryClick(repository : RepositoryViewModel) {
        repositoriesRouter.showRepositoryDetails(repository)
    }

    fun searchRepositoriesByName(text : String) : Observable<List<RepositoryViewModel>>{
        return searchInteractor.execute(text)
    }

    fun onLoadMore(text : String, page: Int) {
        searchInteractor
            .execute(text, page)
            .subscribe(
                {view?.appendToRepositoryList(it)},
                {view?.showError()})
            .addTo(disposables)
    }


}