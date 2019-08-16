package liuuu.laurence.foundationprojectmvvmandroid.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import liuuu.laurence.foundationprojectmvvmandroid.databinding.ListItemGithubUserBinding
import liuuu.laurence.foundationprojectmvvmandroid.model.GitHubUser

class GitHubUserAdapter() :
        ListAdapter<GitHubUser, GitHubUserAdapter.ViewHolder>(GitHubUserDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemGithubUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitHubUser) {
            binding.githubUser = item
            binding.nameTextView.text = item.login
            binding.typeTextView.text = item.type
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemGithubUserBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    class GitHubUserDiffCallback : DiffUtil.ItemCallback<GitHubUser>() {
        override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
            return oldItem == newItem
        }
    }
}