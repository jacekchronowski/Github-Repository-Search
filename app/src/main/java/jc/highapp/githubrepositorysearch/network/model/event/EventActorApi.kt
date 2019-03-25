package jc.highapp.githubrepositorysearch.network.model.event

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class EventActorApi(
    @JsonField var id : Long = 0L,
    @JsonField var login : String? = "",
    @JsonField(name = ["display_login"]) var displayLogin : String? = "",
    @JsonField(name = ["gravatar_id"]) var gravatarId : String? = "",
    @JsonField var url : String? = "",
    @JsonField(name = ["avatar_url"]) var avatarUrl : String? = ""
)


