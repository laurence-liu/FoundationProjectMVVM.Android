package liuuu.laurence.foundationprojectmvvmandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import liuuu.laurence.foundationprojectmvvmandroid.databinding.FragmentGithubBinding
import liuuu.laurence.foundationprojectmvvmandroid.github.GitHubClickListener
import liuuu.laurence.foundationprojectmvvmandroid.github.GitHubUserAdapter
import liuuu.laurence.foundationprojectmvvmandroid.github.GitHubViewModel
import liuuu.laurence.foundationprojectmvvmandroid.github.GitHubViewModelFactory
import timber.log.Timber

class GitHubFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val binding = FragmentGithubBinding.inflate(inflater)

        val viewModelFactory = GitHubViewModelFactory()

        val githubViewMode = ViewModelProviders.of(this, viewModelFactory).get(GitHubViewModel::class.java)

        binding.lifecycleOwner = this

        binding.gitHubViewModel = githubViewMode

        val adapter = GitHubUserAdapter(GitHubClickListener {
            githubViewMode.onGitHubUserClicked(it)
        })

        binding.githubUserRecyclerView.adapter = adapter

        githubViewMode.githubUserList.observe(this, Observer {
            adapter.submitList(it)
        })

        githubViewMode.navigateToGitHubUserDetail.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Timber.i(it)
            }
        })

        return binding.root
    }

}