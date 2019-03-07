package jc.highapp.githubrepositorysearch.search.view

import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel

interface SearchView {
    fun loadRepositoryList(repositories: List<RepositoryViewModel>)
    fun appendToRepositoryList(repositories: List<RepositoryViewModel>)
    fun showError()
    fun initList()

}