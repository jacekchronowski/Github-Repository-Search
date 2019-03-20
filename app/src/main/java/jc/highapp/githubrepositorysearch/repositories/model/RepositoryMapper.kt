package jc.highapp.githubrepositorysearch.repositories.model

import jc.highapp.githubrepositorysearch.network.model.repository.RepositoryApi

class RepositoryMapper {

    fun mapToViewModel(repositoryApi: RepositoryApi) : RepositoryViewModel {
        return RepositoryViewModel(
            repositoryApi.id,
            repositoryApi.name ?: "",
            repositoryApi.owner?.avatarUrl,
            repositoryApi.htmlUrl ?: "",
            repositoryApi.description ?: "",
            repositoryApi.stargazersCount,
            repositoryApi.language ?: "")
    }
}