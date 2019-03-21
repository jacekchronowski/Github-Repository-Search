package jc.highapp.githubrepositorysearch.events.interactor

import io.reactivex.Observable
import jc.highapp.githubrepositorysearch.network.api.GitHubApi

class EventsInteractor(private val gitHubApi: GitHubApi) {

    fun execute() : Observable<List<EventsViewModel>> {

    }
}