package jc.highapp.githubrepositorysearch.network.model.event

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class EventApi(
    @JsonField var id : Long = 0L,
    @JsonField var type : String? = null,
    @JsonField var actor : EventActorApi? = null,
    @JsonField var repo : EventRepoApi? = null,
    @JsonField var payload : Map<String, Any> = mapOf(),
    @JsonField var public : Boolean = true,
    @JsonField(name = ["created_at"]) var createdAt : String? = null,
    @JsonField var org : EventOrgApi? = null
)