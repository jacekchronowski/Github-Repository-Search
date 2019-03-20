package jc.highapp.githubrepositorysearch.interactor

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import jc.highapp.githubrepositorysearch.network.api.GitHubApi
import jc.highapp.githubrepositorysearch.network.model.repository.RepositoriesResponse
import jc.highapp.githubrepositorysearch.network.model.repository.RepositoryApi
import jc.highapp.githubrepositorysearch.repositories.interactor.RepositoryInteractor
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryMapper
import jc.highapp.githubrepositorysearch.utils.RxTestRuleScheduler
import okhttp3.ResponseBody
import org.junit.*
import retrofit2.Response

class RepositoryInteractorTest {

    private lateinit var sut : RepositoryInteractor
    private val gitHubApi : GitHubApi = mock()
    private val repositoryMapper : RepositoryMapper = mock()

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
        sut = RepositoryInteractor(gitHubApi, repositoryMapper)
    }

    @Test
    fun testOnEmptyText() {
        sut.execute("", 1)
            .test()
            .assertValue { it.isEmpty() }

    }

    @Test
    fun testOnSearchSuccesfull() {

        val repositories = listOf(RepositoryApi())

        whenever(gitHubApi.getRepositoriesByName(any(), any())).thenReturn(Observable.just(Response.success(
            RepositoriesResponse(items = repositories)
        )))

        sut.execute("text",2)
            .test()
            .assertComplete()
    }

    @Test
    fun testOnSearchFailure() {

        whenever(gitHubApi.getRepositoriesByName(any(), any()))
            .thenReturn(Observable.just(Response.error(400, ResponseBody.create(null, ByteArray(16)))))

        sut.execute("text",2)
            .test()
            .assertNoValues()
    }

    @Test
    fun testOnSearchQuery() {

        whenever(gitHubApi.getRepositoriesByName(any(), any())).thenReturn(Observable.just(Response.success(
            RepositoriesResponse()
        )))

        sut.execute("text",2)
            .test()

        verify(gitHubApi).getRepositoriesByName("text in:name", 2)
    }
}