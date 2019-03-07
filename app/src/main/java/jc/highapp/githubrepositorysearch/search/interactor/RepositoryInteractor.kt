package jc.highapp.githubrepositorysearch.search.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jc.highapp.githubrepositorysearch.network.api.GitHubApi
import jc.highapp.githubrepositorysearch.search.model.RepositoryMapper
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel

class RepositoryInteractor(
    private val gitHubApi: GitHubApi,
    private val repositoryMapper : RepositoryMapper) {

    fun execute(searchText : String, pageNumber : Int = 1) : Observable<List<RepositoryViewModel>> {

        if(searchText.isEmpty()) {
            return Observable.just(listOf())
        }

        return gitHubApi
            .getRepositoriesByName(
                "$searchText in:name",
                pageNumber)
            .map { it.items }
            .map { it.map { repositoryMapper.mapToViewModel(it) } }
    }
}