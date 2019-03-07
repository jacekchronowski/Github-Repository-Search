package jc.highapp.githubrepositorysearch.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jc.highapp.githubrepositorysearch.R
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel

class RepositoryListAdapter(
    private val onRepositoryClick : (RepositoryViewModel) -> Unit) : RecyclerView.Adapter<RepositoryViewHolder>() {

    private val repositories : MutableList<RepositoryViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repository_item_layout, parent, false))
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position], onRepositoryClick)
    }

    fun loadRepositoryList(repositories : List<RepositoryViewModel>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)
        notifyDataSetChanged()
    }

    fun appendToRepositoriesList(repositories : List<RepositoryViewModel>) {
        this.repositories.addAll(repositories)
        notifyDataSetChanged()
    }

}