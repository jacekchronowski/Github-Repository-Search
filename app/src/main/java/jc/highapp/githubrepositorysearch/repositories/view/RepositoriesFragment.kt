package jc.highapp.githubrepositorysearch.repositories.view

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import io.reactivex.disposables.Disposable
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.mvp.BaseFragment
import jc.highapp.githubrepositorysearch.repositories.adapter.RepositoryListAdapter
import jc.highapp.githubrepositorysearch.repositories.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.repositories.presenter.RepositoriesPresenter
import jc.highapp.githubrepositorysearch.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.search_fragment_layout.*
import org.kodein.di.generic.instance

class RepositoriesFragment : BaseFragment<RepositoriesPresenter, RepositoriesView>(), RepositoriesView {

    override val presenter: RepositoriesPresenter by instance()
    override val layoutResId: Int = R.layout.search_fragment_layout

    private var searchDisposable : Disposable? = null

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            it.getString(SEARCH_KEY_WORD)?.let {
                et_search.setText(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        subscribeToTextViewChanges()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_KEY_WORD, et_search.text.toString())
    }

    private fun subscribeToTextViewChanges() {

        searchDisposable = et_search
            .afterTextChangeEvents()
            .flatMap { presenter.searchRepositoriesByName(it.editable.toString()) }
            .subscribe(
                {
                    scrollListener.resetState()
                    loadRepositoryList(it)
                },
                {
                    showError()
                })
    }

    override fun onPause() {
        super.onPause()
        unsubscribeToTextViewChanges()
    }

    private fun unsubscribeToTextViewChanges() {
        searchDisposable?.let {
            if(!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_search_results.removeOnScrollListener(scrollListener)
    }

    override fun initList() {
        rv_search_results.apply {
            layoutManager = LinearLayoutManager(activity).apply { orientation = RecyclerView.VERTICAL }
            adapter = RepositoryListAdapter(presenter::onRepositoryClick)
        }
        scrollListener = object : EndlessRecyclerViewScrollListener(rv_search_results.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.onLoadMore(et_search.text.toString(), page)
            }
        }

        rv_search_results.addOnScrollListener(scrollListener)
    }

    override fun loadRepositoryList(repositories: List<RepositoryViewModel>) {
        with(rv_search_results.adapter as RepositoryListAdapter) {
            loadRepositoryList(repositories)
        }
    }

    override fun appendToRepositoryList(repositories: List<RepositoryViewModel>) {
        with(rv_search_results.adapter as RepositoryListAdapter) {
            appendToRepositoriesList(repositories)
        }
    }

    override fun showError() {
        activity?.let {
            Toast.makeText(it, R.string.error_message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val SEARCH_KEY_WORD = "SEARCH_KEY_WORD"
    }

}