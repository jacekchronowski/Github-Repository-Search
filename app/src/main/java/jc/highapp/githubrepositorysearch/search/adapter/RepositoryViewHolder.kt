package jc.highapp.githubrepositorysearch.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jc.highapp.githubrepositorysearch.search.model.RepositoryViewModel
import kotlinx.android.synthetic.main.repository_item_layout.view.*

class RepositoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(repositoryViewModel: RepositoryViewModel, onRepositoryClick: (RepositoryViewModel) -> Unit) {

        Glide
            .with(itemView)
            .load(repositoryViewModel.ownerAvatarUrl)
            .centerCrop()
            .into(itemView.iv_repo_owner_avatar)

        itemView.tv_repository_name.text = repositoryViewModel.name
        itemView.tv_repository_desc.text = repositoryViewModel.description
        itemView.tv_repository_stars.text = repositoryViewModel.starsCount.toString()
        itemView.tv_programming_language.text = repositoryViewModel.language
        itemView.setOnClickListener { onRepositoryClick(repositoryViewModel) }
    }
}