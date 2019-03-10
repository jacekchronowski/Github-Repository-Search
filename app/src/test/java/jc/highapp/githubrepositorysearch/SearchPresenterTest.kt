package jc.highapp.githubrepositorysearch

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jc.highapp.githubrepositorysearch.search.interactor.SearchInteractor
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.search.presenter.SearchPresenter
import jc.highapp.githubrepositorysearch.search.router.SearchRouter
import jc.highapp.githubrepositorysearch.search.view.SearchView
import jc.highapp.githubrepositorysearch.utils.RxTestRuleScheduler
import org.junit.*
import java.io.IOException

class SearchPresenterTest {

    private lateinit var sut : SearchPresenter

    private val searchInteractor : SearchInteractor = mock()
    private val searchRouter : SearchRouter = mock()
    private val view : SearchView = mock()

    @Rule @JvmField var testRule = RxTestRuleScheduler()

    companion object {
        @JvmStatic @AfterClass
        fun tearDownClass() {
            RxAndroidPlugins.reset()
        }

        @JvmStatic @BeforeClass
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun setUp() {
        sut = SearchPresenter(searchInteractor, searchRouter)
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
        verify(searchRouter).showRepositoryDetails(repository)
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