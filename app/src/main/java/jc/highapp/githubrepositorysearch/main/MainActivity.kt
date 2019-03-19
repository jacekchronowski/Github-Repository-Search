package jc.highapp.githubrepositorysearch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }

    private val navigator : Navigator by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator.bindRoot(this)
        if(savedInstanceState == null) {
            navigator.startFlow()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.unbindRoot()
    }
}
