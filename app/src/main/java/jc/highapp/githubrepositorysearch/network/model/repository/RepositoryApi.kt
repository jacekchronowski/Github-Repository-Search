package jc.highapp.githubrepositorysearch.network.model.repository

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import jc.highapp.githubrepositorysearch.network.model.user.UserApi

@JsonObject
data class RepositoryApi(
    @JsonField var id : Long = 0L,
    @JsonField(name = ["node_id"]) var nodeId : String? = "",
    @JsonField var name : String? = "",
    @JsonField(name = ["full_name"]) var fullName : String? = "",
    @JsonField var owner : UserApi? = null,
    @JsonField(name = ["html_url"]) var htmlUrl : String? = "",
    @JsonField var description : String? = "",
    @JsonField(name = ["stargazers_count"]) var stargazersCount : Long = 0L,
    @JsonField var language : String? = "")
