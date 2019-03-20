package jc.highapp.githubrepositorysearch.repositories.router

import jc.highapp.githubrepositorysearch.navigation.Navigator
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryViewModel

class RepositoriesRouter(private val navigator : Navigator) {
    fun showRepositoryDetails(repositoryViewModel: RepositoryViewModel) {
        navigator.showPageInBrowser(repositoryViewModel.repositoryUrl)
    }

}