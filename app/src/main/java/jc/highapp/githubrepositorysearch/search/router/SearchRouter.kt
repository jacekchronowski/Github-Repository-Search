package jc.highapp.githubrepositorysearch.search.router

import jc.highapp.githubrepositorysearch.navigation.Navigator
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel

class SearchRouter(private val navigator : Navigator) {
    fun showRepositoryDetails(repositoryViewModel: RepositoryViewModel) {
        navigator.showPageInBrowser(repositoryViewModel.repositoryUrl)
    }

}