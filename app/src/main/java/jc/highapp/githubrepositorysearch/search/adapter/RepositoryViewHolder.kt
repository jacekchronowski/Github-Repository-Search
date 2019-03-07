package jc.highapp.githubrepositorysearch.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import kotlinx.android.synthetic.main.repository_item_layout.view.*

class RepositoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(repositoryViewModel: RepositoryViewModel, onRepositoryClick: (RepositoryViewModel) -> Unit) {
        itemView.tv_repository_name.text = repositoryViewModel.name
        itemView.tv_repository_stars.text = "Repository stats ${repositoryViewModel.starsCount}"
        itemView.setOnClickListener { onRepositoryClick(repositoryViewModel) }
    }
}