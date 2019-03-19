package jc.highapp.githubrepositorysearch.search.presenter

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import jc.highapp.githubrepositorysearch.main.BasePresenter
import jc.highapp.githubrepositorysearch.search.interactor.SearchInteractor
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.search.router.SearchRouter
import jc.highapp.githubrepositorysearch.search.view.SearchView
import jc.highapp.githubrepositorysearch.utils.addTo

class SearchPresenter(
    private val searchInteractor : SearchInteractor,
    private val searchRouter: SearchRouter) : BasePresenter<SearchView>(){


    override fun onInit() {
        view?.initList()
    }

    fun onRepositoryClick(repository : RepositoryViewModel) {
        searchRouter.showRepositoryDetails(repository)
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