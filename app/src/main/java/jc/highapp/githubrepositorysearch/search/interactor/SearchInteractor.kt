package jc.highapp.githubrepositorysearch.search.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import java.util.concurrent.TimeUnit

class SearchInteractor(private val repositoryInteractor: RepositoryInteractor) {

    fun execute(text : String) : Observable<List<RepositoryViewModel>> {
        return Observable.just(text)
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged ()
            .switchMap { repositoryInteractor.execute(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}