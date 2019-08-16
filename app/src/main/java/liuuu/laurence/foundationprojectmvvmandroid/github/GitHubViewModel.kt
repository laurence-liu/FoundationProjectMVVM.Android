package liuuu.laurence.foundationprojectmvvmandroid.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import liuuu.laurence.foundationprojectmvvmandroid.Event
import liuuu.laurence.foundationprojectmvvmandroid.SingleLiveEvent
import liuuu.laurence.foundationprojectmvvmandroid.model.GitHubUser
import liuuu.laurence.foundationprojectmvvmandroid.network.GitHubApi
import java.lang.Exception

class GitHubViewModel : ViewModel() {
    private val _githubUserList = MutableLiveData<List<GitHubUser>>()
    val githubUserList: LiveData<List<GitHubUser>>
        get() = _githubUserList

    private val _navigateToGitHubUserDetail = SingleLiveEvent<Event<String>>()
    val navigateToGitHubUserDetail: LiveData<Event<String>>
        get() = _navigateToGitHubUserDetail

    var viewModelJob = Job()
    val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getGitHubUserList()
    }

    private fun getGitHubUserList() {
        coroutineScope.launch {
            val gitHubDeferred = GitHubApi.retrofitService.getGitHubUser(10)

            try {
                val gitHubResponse = gitHubDeferred.await()
//                _githubUserList.value = gitHubResponse.filter {
//                    it.login[0] > 'v'
//                }
                _githubUserList.value = gitHubResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onGitHubUserClicked(login: String) {
        _navigateToGitHubUserDetail.value = Event(login)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}