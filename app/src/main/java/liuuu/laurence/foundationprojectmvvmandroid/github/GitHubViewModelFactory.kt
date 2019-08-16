package liuuu.laurence.foundationprojectmvvmandroid.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GitHubViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            return GitHubViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}