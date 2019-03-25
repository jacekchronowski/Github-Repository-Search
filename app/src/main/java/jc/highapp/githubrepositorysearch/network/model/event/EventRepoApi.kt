package jc.highapp.githubrepositorysearch.network.model.event

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class EventRepoApi(
    @JsonField var id: Long = 0L,
    @JsonField var name: String? = "",
    @JsonField var url : String? = "")