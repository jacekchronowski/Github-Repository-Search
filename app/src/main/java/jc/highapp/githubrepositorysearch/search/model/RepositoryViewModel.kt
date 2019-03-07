package jc.highapp.githubrepositorysearch.search.model

data class RepositoryViewModel(
    val id : Long,
    val name : String,
    val ownerAvatarUrl : String?,
    val repositoryUrl : String,
    val description : String,
    val starsCount : Long,
    val language : String)
