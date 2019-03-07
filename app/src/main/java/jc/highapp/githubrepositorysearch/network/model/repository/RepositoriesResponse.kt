package jc.highapp.githubrepositorysearch.network.model.repository

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject

@JsonObject
data class RepositoriesResponse(
    @JsonField(name = ["total_count"]) var totalCount : Long = 0L,
    @JsonField(name = ["incomplete_results"]) var incompleteResults : Boolean = false,
    @JsonField var items : List<RepositoryApi> = listOf())
