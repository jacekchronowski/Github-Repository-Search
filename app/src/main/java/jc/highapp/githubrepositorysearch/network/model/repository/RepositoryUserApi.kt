package jc.highapp.githubrepositorysearch.network.model.repository

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class RepositoryUserApi(
    @JsonField var login : String? = "",
    @JsonField var id : Long = 0L,
    @JsonField(name = ["avatar_url"]) var avatarUrl : String? = null,
    @JsonField var url : String? = "",
    @JsonField var type : String? = "")