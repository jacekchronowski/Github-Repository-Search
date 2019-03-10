package jc.highapp.githubrepositorysearch.search.presenter

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import jc.highapp.githubrepositorysearch.search.interactor.SearchInteractor
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.search.router.SearchRouter
import jc.highapp.githubrepositorysearch.search.view.SearchView
import jc.highapp.githubrepositorysearch.utils.addTo

class SearchPresenter(
    private val searchInteractor : SearchInteractor,
    private val searchRouter: SearchRouter) {

    private var view: SearchView? = null
    private var disposables : CompositeDisposable? = null

    fun onResume() {
        disposables = CompositeDisposable()
    }

    fun onPause() {
        disposables?.dispose()
        disposables = null
    }

    fun bindView(searchView: SearchView) {
        this.view = searchView
    }

    fun unbindView() {
        this.view = null
    }

    fun onInit() {
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