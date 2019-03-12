package jc.highapp.githubrepositorysearch.main

import android.app.Application
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import jc.highapp.githubrepositorysearch.constants.Constants
import jc.highapp.githubrepositorysearch.navigation.Navigator
import jc.highapp.githubrepositorysearch.network.api.GitHubApi
import jc.highapp.githubrepositorysearch.network.interceptors.HeaderInterceptor
import jc.highapp.githubrepositorysearch.network.interceptors.HttpLogger
import jc.highapp.githubrepositorysearch.search.interactor.RepositoryInteractor
import jc.highapp.githubrepositorysearch.search.interactor.SearchInteractor
import jc.highapp.githubrepositorysearch.search.model.RepositoryMapper
import jc.highapp.githubrepositorysearch.search.presenter.SearchPresenter
import jc.highapp.githubrepositorysearch.search.router.SearchRouter
import okhttp3.Interceptor
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor



class GithubApplication : Application(), KodeinAware {
    override val kodein: Kodein by Kodein.lazy {
        bind<SearchPresenter>() with provider { SearchPresenter(instance(), instance()) }
        bind<SearchInteractor>() with provider { SearchInteractor(instance()) }
        bind<SearchRouter>() with provider { SearchRouter(instance()) }
        bind<RepositoryInteractor>() with provider { RepositoryInteractor(instance(), instance())}
        bind<GitHubApi>() with singleton {
            Retrofit.Builder()
                .baseUrl(Constants.GITHUB_BASE_URL)
                .addConverterFactory(LoganSquareConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder()
                    .addInterceptor(HeaderInterceptor())
                    .addInterceptor(HttpLoggingInterceptor(HttpLogger()).apply {
                        level = HttpLoggingInterceptor.Level.BODY })
                    .build())
                .build()
                .create(GitHubApi::class.java)
        }
        bind<Navigator>() with singleton { Navigator() }
        bind<RepositoryMapper>() with singleton { RepositoryMapper() }

    }
}