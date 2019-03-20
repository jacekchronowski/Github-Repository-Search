package jc.highapp.githubrepositorysearch.repositories.interactor

import io.reactivex.Observable
import jc.highapp.githubrepositorysearch.network.api.GitHubApi
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryMapper
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryViewModel

class RepositoryInteractor(
    private val gitHubApi: GitHubApi,
    private val repositoryMapper : RepositoryMapper) {

    fun execute(searchText : String, pageNumber : Int) : Observable<List<RepositoryViewModel>> {

        if(searchText.isEmpty()) {
            return Observable.just(listOf())
        }

        return gitHubApi
            .getRepositoriesByName(
                "$searchText in:name",
                pageNumber)
            .filter { it.isSuccessful}
            .map { it.body()?.items ?: listOf()}
            .map { it.map { repositoryMapper.mapToViewModel(it) } }
    }
}