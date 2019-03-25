package jc.highapp.githubrepositorysearch.events.mapper

import jc.highapp.githubrepositorysearch.events.model.EventsViewModel
import jc.highapp.githubrepositorysearch.network.model.event.EventApi

fun EventApi.createCreatedEventViewModel() : EventsViewModel? {
    payload["ref_type"]?.let {
        when(it) {
            "repository" -> this.createCreatedEventRepository()
            "tag" -> this.createCreatedEventTag()
            "branch" -> this.createCreatedEventBranch()
            else -> null
        }
    }

}