package jc.highapp.githubrepositorysearch.utils

import android.content.Intent
import android.net.Uri
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import jc.highapp.githubrepositorysearch.R


fun FragmentActivity.showFragment(fragment: Fragment, @IdRes containerResId : Int) {
    this.supportFragmentManager
        .beginTransaction()
        .replace(containerResId, fragment, fragment::class.java.simpleName)
        .commit()
}

fun FragmentActivity.sendAction(actionType : String, uri : Uri) {
    val intent = Intent(actionType)
    intent.data = uri
    if(intent.resolveActivity(this.packageManager) != null) {
        this.startActivity(intent)
    }
}