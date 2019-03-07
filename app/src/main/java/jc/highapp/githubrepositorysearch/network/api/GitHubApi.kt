package jc.highapp.githubrepositorysearch.network.api

import io.reactivex.Observable
import jc.highapp.githubrepositorysearch.network.model.repository.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    fun getRepositoriesByName(
        @Query("q") searchQuery : String?,
        @Query("pageNumber") pageNumber : Int) : Observable<RepositoriesResponse>
}