package jc.highapp.githubrepositorysearch.mvp

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseView> {

    protected var disposables : CompositeDisposable? = null
    protected var view : V? = null

    abstract fun onInit()

    fun onCreateView() {
        disposables = CompositeDisposable()
    }

    fun onDestroyView() {
        disposables?.let {
            if(!it.isDisposed) {
                it.dispose()
            }
        }

    }

    fun bindView(view : V) {
        this.view = view
    }

    fun ubindView() {
        this.view = null
    }

}