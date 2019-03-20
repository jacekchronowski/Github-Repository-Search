package jc.highapp.githubrepositorysearch.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

abstract class BaseFragment<P : BasePresenter<V>, V : BaseView> : Fragment(), BaseView, KodeinAware{

    override val kodein: Kodein by lazy { (activity as KodeinAware).kodein }
    abstract val layoutResId : Int
    abstract val presenter : P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(layoutResId, container, false)
        presenter.bindView(this as V)
        presenter.onCreateView()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onInit()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
        presenter.ubindView()
    }
}