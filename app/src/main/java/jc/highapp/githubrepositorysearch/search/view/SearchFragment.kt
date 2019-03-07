package jc.highapp.githubrepositorysearch.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.widget.textChangeEvents
import io.reactivex.disposables.Disposable
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.search.adapter.RepositoryListAdapter
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import jc.highapp.githubrepositorysearch.search.presenter.SearchPresenter
import kotlinx.android.synthetic.main.search_fragment_layout.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SearchFragment : Fragment(), KodeinAware, SearchView {

    override val kodein: Kodein by lazy { (activity as KodeinAware).kodein }
    private val presenter: SearchPresenter by instance()
    private var searchDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.search_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onInit()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        subscribeToTextViewChanges()
    }

    private fun subscribeToTextViewChanges() {
        searchDisposable = et_search
            .textChangeEvents()
            .flatMap { presenter.searchRepositoriesByName(it.text.toString()) }
            .subscribe(
                {
                    loadRepositoryList(it)
                },
                {
                    showError()
                })
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
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
        presenter.detachView()
    }

    override fun initList() {
        rv_search_results.apply {
            layoutManager = LinearLayoutManager(activity).apply { orientation = RecyclerView.VERTICAL }
            adapter = RepositoryListAdapter(presenter::onRepositoryClick)
        }
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

}