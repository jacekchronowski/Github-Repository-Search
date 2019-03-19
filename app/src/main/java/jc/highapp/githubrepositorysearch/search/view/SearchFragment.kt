package jc.highapp.githubrepositorysearch.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import io.reactivex.disposables.Disposable
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.main.BaseFragment
import jc.highapp.githubrepositorysearch.search.adapter.RepositoryListAdapter
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.search.presenter.SearchPresenter
import jc.highapp.githubrepositorysearch.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.search_fragment_layout.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SearchFragment : BaseFragment<SearchPresenter,SearchView>(), KodeinAware, SearchView {

    override val kodein: Kodein by lazy { (activity as KodeinAware).kodein }
    override val presenter: SearchPresenter by instance()
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