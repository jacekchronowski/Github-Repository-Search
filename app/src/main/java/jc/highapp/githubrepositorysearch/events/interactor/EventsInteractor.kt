package jc.highapp.githubrepositorysearch.events.interactor

import io.reactivex.Observable
import jc.highapp.githubrepositorysearch.events.mapper.EventsMapper
import jc.highapp.githubrepositorysearch.events.model.EventsViewModel
import jc.highapp.githubrepositorysearch.network.api.GitHubApi

class EventsInteractor(
    private val gitHubApi: GitHubApi,
    private val eventsMapper : EventsMapper) {

    fun execute(pageNumber : Int = 1) : Observable<List<EventsViewModel>> {
        return gitHubApi
            .getEvents(pageNumber)
            .map { it.body()?.mapNotNull { eventsMapper.mapToViewModel(it) } }
    }
}