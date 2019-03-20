package jc.highapp.githubrepositorysearch.presenter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import jc.highapp.githubrepositorysearch.repositories.interactor.SearchInteractor
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.repositories.presenter.RepositoriesPresenter
import jc.highapp.githubrepositorysearch.repositories.router.RepositoriesRouter
import jc.highapp.githubrepositorysearch.repositories.view.RepositoriesView
import org.junit.*
import java.io.IOException

class RepositoriesPresenterTest {

    private lateinit var sut : RepositoriesPresenter

    private val searchInteractor : SearchInteractor = mock()
    private val repositoriesRouter : RepositoriesRouter = mock()
    private val view : RepositoriesView = mock()

    @Before
    fun setUp() {
        sut = RepositoriesPresenter(searchInteractor, repositoriesRouter)
        sut.bindView(view)
    }

    @After
    fun tearDown() {
        sut.unbindView()
    }

    @Test
    fun testOnInit() {
        sut.onInit()
        verify(view).initList()
    }

    @Test
    fun testOnRepositoryClick(){
        val repository : RepositoryViewModel = mock()

        sut.onRepositoryClick(repository)
        verify(repositoriesRouter).showRepositoryDetails(repository)
    }

    @Test
    fun testSearchRepositoriesByName() {

        sut.searchRepositoriesByName("text")
        verify(searchInteractor).execute("text", 1)
    }

    @Test
    fun testOnLoadMoreSuccess() {

        val repositories = listOf(RepositoryViewModel(
            1,
            "name",
            "",
            "",
            "desc",
            1,
            "java"
        ))

        whenever(searchInteractor.execute("text", 2)).thenReturn(Observable.just(repositories))

        sut.onLoadMore("text", 2)
        verify(view).appendToRepositoryList(repositories)
    }

    @Test
    fun onLoadMoreFailure() {

        whenever(searchInteractor.execute("text", 2)).thenReturn(Observable.error(IOException()))

        sut.onLoadMore("text", 2)
        verify(view).showError()
    }
}