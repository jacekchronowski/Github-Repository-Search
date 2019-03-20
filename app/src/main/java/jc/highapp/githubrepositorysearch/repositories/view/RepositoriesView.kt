package jc.highapp.githubrepositorysearch.repositories.view

import jc.highapp.githubrepositorysearch.main.BaseView
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryViewModel

interface RepositoriesView : BaseView{
    fun loadRepositoryList(repositories: List<RepositoryViewModel>)
    fun appendToRepositoryList(repositories: List<RepositoryViewModel>)
    fun showError()
    fun initList()

}