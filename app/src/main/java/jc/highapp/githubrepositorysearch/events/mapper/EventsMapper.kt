package jc.highapp.githubrepositorysearch.events.mapper

import jc.highapp.githubrepositorysearch.events.model.EventsViewModel
import jc.highapp.githubrepositorysearch.network.model.event.EventApi

class EventsMapper {

    fun mapToViewModel(eventApi : EventApi) : EventsViewModel?{

        return when(eventApi.type) {
            "CreatedEvent" -> createCreatedEventModel(eventApi)
            else -> null
        }
    }
}